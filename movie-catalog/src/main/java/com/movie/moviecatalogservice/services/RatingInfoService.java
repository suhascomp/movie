package com.movie.moviecatalogservice.services;

import com.movie.moviecatalogservice.models.MovieInfo;
import com.movie.moviecatalogservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RatingInfoService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackRating")
    public Rating getRating(MovieInfo movieInfo) {
        return restTemplate.getForObject("http://movie-rating/rating/getById/" + movieInfo.getMovieId(), Rating.class);
    }

    public Rating getFallbackRating() {
        return new Rating(12, 9);
    }
}
