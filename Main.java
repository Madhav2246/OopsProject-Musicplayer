import java.util.*;
public class Main {
    private static ArrayList<Album> albums = new ArrayList<>();
    public static void main(String[] args) {
        Album album = new Album("NCS","Tobu");
        album.addSong("Candyland",3.19);
        album.addSong("Hope",4.46);
        album.addSong("Infectious",4.17);
        album.addSong("Lost",3.06);
        album.addSong("Sunburst",3.11);

        // Added Album in a Arraylist
        albums.add(album);
        album = new Album("NCS2","JPB");
        album.addSong("High",3.13);
        album.addSong("What I Want",2.45);
        album.addSong("Top Floor",3.36);
        album.addSong("All Stops Now",2.26);
        album.addSong("Get Over You",3.29);

        // Added Album in a Arraylist
        albums.add(album);

        LinkedList<Songs> Playlist1 = new LinkedList<>();

        albums.get(0).addtoPlaylist("Hope",Playlist1);
        albums.get(0).addtoPlaylist("Lost",Playlist1);
        albums.get(1).addtoPlaylist("Top Floor",Playlist1);
        albums.get(1).addtoPlaylist("High",Playlist1);

        play(Playlist1);
    }

    private static String printTitle(String title){
        // Songs{title='Hope', duration=4.46}
        int l=title.length();
        String duration=title.charAt(l-5)+":"+title.charAt(l-3)+title.charAt(l-2);
        String name=title.substring(13,l-17);
        title = name+" "+duration;
        // Hope 4:46
        return title;
    }

    private static void play(LinkedList<Songs> Playlist){
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Songs> listIterator = Playlist.listIterator();

        if(Playlist.size() == 0){
            System.out.println("This playlist is currently empty.");
        }
        else {
            System.out.println("Now playing: "+printTitle(listIterator.next().toString()));
            printMenu();
        }
        while(!quit){
            System.out.print("\nEnter Choice : ");
            int action = sc.nextInt();
            sc.nextLine();
            switch(action){
                case 0:
                    System.out.println("Playlist complete");
                    System.out.println("||********Thanks for Visit my Music App***********||");
                    quit=true;
                    break;

                case 1:
                    if(!forward){
                        if(listIterator.hasNext()){
                            listIterator.next();
                        }
                        forward=true;
                    }
                    if(listIterator.hasNext()){
                        System.out.println("Now Playing: "+printTitle(listIterator.next().toString()));
                    }
                    else{
                        System.out.println("This playlist is currently empty.");
                        forward=false;
                    }
                    break;
                case 2:
                    if(forward){
                        if(listIterator.hasPrevious()){
                            listIterator.previous();
                        }
                        forward=false;
                    }
                    if(listIterator.hasPrevious()){
                        System.out.println("Now Playing: "+printTitle(listIterator.previous().toString()));
                    }
                    else{
                        System.out.println("This is the first song in this playlist.");
                        forward=false;
                    }
                    break;
                case 3:
                    if(forward){
                        if(listIterator.hasPrevious()){
                            System.out.println("Now Playing: "+printTitle(listIterator.previous().toString()));
                            forward = false;
                        }
                        else{
                            System.out.println("This is the first song in this playlist.");
                        }
                    }
                    else{
                        if(listIterator.hasNext()){
                            System.out.println("Now Playing: "+printTitle(listIterator.next().toString()));
                            forward=true;
                        }
                        else{
                            System.out.println("This playlist is currently empty.");
                        }
                    }
                    break;
                case 4:
                    printList(Playlist);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if(Playlist.size()>0){
                        listIterator.remove();
                        if(listIterator.hasNext()){
                            System.out.println("Now Playing: "+printTitle(listIterator.next().toString()));
                        }
                        else {
                            if(listIterator.hasPrevious()){
                                System.out.println("Now Playing: "+printTitle(listIterator.previous().toString()));
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    private static void printMenu(){
        System.out.println("Available Options ");
        System.out.println("0 - Quit from Music Player\n"
                +"1 - Next Song\n"
                +"2 - Previous Song\n"
                +"3 - Repeat Song\n"
                +"4 - List all songs\n"
                +"5 - Print all available options\n"
                +"6 - Remove current Song from Playlist");
    }
    private static void printList(LinkedList<Songs> Playlist){
        Iterator<Songs> iterator = Playlist.iterator();
        System.out.println("<---------------------------------------------------------------------->");

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("<---------------------------------------------------------------------->");
    }
}