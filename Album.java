import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    private String name;
    private String artist;
    private ArrayList<Songs> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<Songs>();
    }

    public Album() {}

    public Songs findSong(String title) {
        for (Songs checkSong : songs) {
            if (checkSong.getTitle().equals(title))
                return checkSong;
        }
        return null;
    }

    public boolean addSong(String title, double duration) {
        if (findSong(title) == null) {
            songs.add(new Songs(title, duration));
            return true;
        } else {
            return false;
        }
    }

    public boolean addToPlaylist(int trackNo, LinkedList<Songs> playlist) {
        int index = trackNo - 1;
        if (index >= 0 && index < this.songs.size()) {
            playlist.add(this.songs.get(index));
            return true;
        }
        return false;
    }

    public boolean addToPlaylist(String title, LinkedList<Songs> playlist) {
        for (Songs checkSong : this.songs) {
            if (checkSong.getTitle().equals(title)) {
                playlist.add(checkSong);
                return true;
            }
        }
        return false;
    }
}
