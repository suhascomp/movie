package com.movie.movieinfoservice.resource;

import com.movie.movieinfoservice.models.MovieInfo;
import com.movie.movieinfoservice.models.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/info")
public class MovieInfoController {

    @Value("${api-key}")
    String apiKey;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{id}")
    public List<MovieInfo> getMovieInfoById(@PathVariable("id") int id) {
        return Collections.singletonList(
                new MovieInfo( id,"Batman")
        );
    }

    @GetMapping("/movie-summery/{id}")
    public MovieInfo getMovieSummery(@PathVariable("id") int id) {
        Root rr = restTemplate.getForObject("https://api.themoviedb.org/3/movie/550?api_key="+apiKey, Root.class);
        rr.getTitle();
        MovieInfo movieInfo = new MovieInfo(id, rr.getTitle());
        movieInfo.setDesc(rr.getOverview());
        return movieInfo;
    }

}
