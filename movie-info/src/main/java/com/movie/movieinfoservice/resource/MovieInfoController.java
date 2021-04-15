package com.movie.movieinfoservice.resource;

import com.movie.movieinfoservice.models.MovieInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/info")
public class MovieInfoController {

    @GetMapping("/{id}")
    public List<MovieInfo> getMovieInfoById(@PathVariable("id") int id) {
        return Collections.singletonList(
                new MovieInfo( id,"Batman")
        );
    }
}
