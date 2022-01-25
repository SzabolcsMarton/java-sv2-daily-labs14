package day02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieService {

    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> getMoviesByActor(String actor){
        return movies
                .stream()
                .filter(movie -> movie.getActors().contains(actor))
                .toList();
    }

    public int getLengthOfLongestMovie(){
        return movies
                .stream()
                .mapToInt(Movie::getLength)
                .max()
                .orElseThrow(()-> new IllegalStateException("Movies list is empty"));
    }

}
