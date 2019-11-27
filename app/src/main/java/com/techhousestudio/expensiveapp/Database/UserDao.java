package com.techhousestudio.expensiveapp.Database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert()
    void insertUser(User user);

    @Query("SELECT * FROM expense " +
            "INNER JOIN user_repo_join " +
            "ON expense.eid=user_repo_join.expenseId " +
            "WHERE user_repo_join.incomeId=:incomeId")
    List<Expense> getExpense(final int incomeId);

    @Query("SELECT * FROM income " +
            "INNER JOIN user_repo_join " +
            "ON income.id=user_repo_join.incomeId " +
            "WHERE user_repo_join.expenseId=:expenseId")
    List<Income> getSongsForPlaylist(final int expenseId);

    @Insert()
    void insertTotalAmount(User user);


    /*@Query("SELECT * FROM playlist " +
            "INNER JOIN playlist_song_join " +
            "ON playlist.id=playlist_song_join.playlistId " +
            "WHERE playlist_song_join.songId=:songId")
    List<Playlist> getPlaylistsForSong(final int songId);

    @Query("SELECT * FROM song " +
            "INNER JOIN playlist_song_join " +
            "ON song.id=playlist_song_join.songId " +
            "WHERE playlist_song_join.playlistId=:playlistId")
    List<Song> getSongsForPlaylist(final int playlistId);*/
}
