package com.movie.moviecatalogservice.services;

import com.movie.moviecatalogservice.models.MovieInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackMovieInfo")
    public MovieInfo getMovieInfo(int id) {
        MovieInfo movieInfo = restTemplate.getForObject("http://movie-info/info/movie-summery/" + id, MovieInfo.class);
        return movieInfo;
    }
    public MovieInfo getFallbackMovieInfo(int id) {
        return new MovieInfo(id, "NoName", "noDesc");
    }
}
