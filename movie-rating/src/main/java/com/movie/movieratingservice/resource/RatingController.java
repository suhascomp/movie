package com.movie.movieratingservice.resource;

import com.movie.movieratingservice.models.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @GetMapping("/{movieId}")
    public List<Rating> getRatingById(@PathVariable("movieId") int movieId) {
        return Collections.singletonList(
                new Rating(movieId, 4)
        );
    }

    @GetMapping("/getById/{id}")
    public Rating getById(@PathVariable("id") int movieId) {
        return new Rating(movieId, 4);
    }
}
