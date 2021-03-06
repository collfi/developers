package sk.cll.mewsapp.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sk.cll.mewsapp.data.Photo;

public interface PhotoService {

    @GET("photos")
    Observable<List<Photo>> getPhotos(@Query("_start") int start, @Query("_limit") int limit);
}
