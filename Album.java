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
    public Album(){

    }
    public  Songs findSong(String title){
        for(Songs checkSong : songs){
            if(checkSong.getTitle().equals(title))
                return checkSong;
        }
        return null;
    }

    public boolean addSong(String title, double duration){
        if(findSong(title)==null){
            songs.add(new Songs(title,duration));
            //System.out.println(title+" has been successfully added.");
            return true;
        }
        else{
            //System.out.println("Song already exists in this list.");
            return false;
        }
    }
    public boolean addtoPlaylist(int trackNo, LinkedList<Songs> Playlist){
        int index = trackNo-1;
        if(index > 0 && index <=this.songs.size()){
            Playlist.add(this.songs.get(index));
            return true;
        }
        //System.out.println("This album does not have song with the track number "+trackNo);
        return false;
    }
    public boolean addtoPlaylist(String title, LinkedList<Songs> Playlist){
        for(Songs checksong : this.songs){
            if(checksong.getTitle().equals(title)){
                Playlist.add(checksong);
                return true;
            }
        }
        //System.out.println("There is no such song in album with the title "+title);
        return false;
    }



}