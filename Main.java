import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Main {
    private static ArrayList<Album> albums = new ArrayList<>();
    private static LinkedList<Songs> playlist1 = new LinkedList<>();
    private static LinkedList<Songs> playlist2 = new LinkedList<>();
    private static LinkedList<Songs> playlist3 = new LinkedList<>();
    private static ListIterator<Songs> listIterator;
    private static boolean forward = true;
    private static LinkedList<Songs> currentPlaylist;

    public static void main(String[] args) {
        Album album = new Album("NCS", "Tobu");
        album.addSong("Candyland", 3.19);
        album.addSong("Hope", 4.46);
        album.addSong("Infectious", 4.17);
        album.addSong("Lost", 3.06);
        album.addSong("Sunburst", 3.11);
        album.addSong("Colors", 3.28);
        album.addSong("Mesmerize", 3.42);
        album.addSong("Higher", 3.57);
        album.addSong("Sunrise", 3.44);
        album.addSong("Life", 4.12);

        albums.add(album);

        album = new Album("NCS2", "JPB");
        album.addSong("High", 3.13);
        album.addSong("What I Want", 2.45);
        album.addSong("Top Floor", 3.36);
        album.addSong("All Stops Now", 2.26);
        album.addSong("Get Over You", 3.29);
        album.addSong("Up & Away", 3.19);
        album.addSong("Fever", 2.58);
        album.addSong("Fire & Thunder", 3.40);
        album.addSong("Backbone", 4.07);
        album.addSong("Escape", 3.50);

        albums.add(album);

        albums.get(0).addToPlaylist("Hope", playlist1);
        albums.get(0).addToPlaylist("Lost", playlist1);
        albums.get(0).addToPlaylist("Candyland", playlist1);
        albums.get(1).addToPlaylist("Top Floor", playlist1);
        albums.get(1).addToPlaylist("High", playlist1);

        albums.get(0).addToPlaylist("Infectious", playlist2);
        albums.get(0).addToPlaylist("Colors", playlist2);
        albums.get(1).addToPlaylist("What I Want", playlist2);
        albums.get(1).addToPlaylist("All Stops Now", playlist2);
        albums.get(1).addToPlaylist("Get Over You", playlist2);

        albums.get(0).addToPlaylist("Sunburst", playlist3);
        albums.get(0).addToPlaylist("Mesmerize", playlist3);
        albums.get(1).addToPlaylist("Up & Away", playlist3);
        albums.get(1).addToPlaylist("Fever", playlist3);
        albums.get(1).addToPlaylist("Fire & Thunder", playlist3);

        currentPlaylist = playlist1;
        listIterator = currentPlaylist.listIterator();

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Music Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        JLabel label = new JLabel("Now Playing: ", SwingConstants.CENTER);
        pane.add(label, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton playNextButton = new JButton("Next Song");
        playNextButton.addActionListener(e -> playNext(label));
        panel.add(playNextButton);

        JButton playPreviousButton = new JButton("Previous Song");
        playPreviousButton.addActionListener(e -> playPrevious(label));
        panel.add(playPreviousButton);

        JButton repeatButton = new JButton("Repeat Song");
        repeatButton.addActionListener(e -> repeatSong(label));
        panel.add(repeatButton);

        JButton removeButton = new JButton("Remove Song");
        removeButton.addActionListener(e -> removeCurrentSong(label));
        panel.add(removeButton);

        JButton showListButton = new JButton("Show Playlist");
        showListButton.addActionListener(e -> showPlaylist());
        panel.add(showListButton);

        JComboBox<String> playlistSelector = new JComboBox<>(new String[]{"Playlist 1", "Playlist 2", "Playlist 3"});
        playlistSelector.addActionListener(e -> switchPlaylist(playlistSelector.getSelectedItem().toString(), label));
        panel.add(playlistSelector);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);

        pane.add(panel, BorderLayout.SOUTH);

        if (currentPlaylist.size() > 0) {
            label.setText("Now Playing: " + printTitle(listIterator.next().toString()));
        } else {
            label.setText("This playlist is currently empty.");
        }

        frame.setVisible(true);
    }

    private static String printTitle(String title) {
        int l = title.length();
        String duration = title.charAt(l - 5) + ":" + title.charAt(l - 3) + title.charAt(l - 2);
        String name = title.substring(13, l - 17);
        return name + " " + duration;
    }

    private static void playNext(JLabel label) {
        if (!forward) {
            if (listIterator.hasNext()) {
                listIterator.next();
            }
            forward = true;
        }
        if (listIterator.hasNext()) {
            label.setText("Now Playing: " + printTitle(listIterator.next().toString()));
        } else {
            label.setText("End of playlist.");
            forward = false;
        }
    }

    private static void playPrevious(JLabel label) {
        if (forward) {
            if (listIterator.hasPrevious()) {
                listIterator.previous();
            }
            forward = false;
        }
        if (listIterator.hasPrevious()) {
            label.setText("Now Playing: " + printTitle(listIterator.previous().toString()));
        } else {
            label.setText("This is the first song in the playlist.");
            forward = false;
        }
    }

    private static void repeatSong(JLabel label) {
        if (forward) {
            if (listIterator.hasPrevious()) {
                label.setText("Now Playing: " + printTitle(listIterator.previous().toString()));
                forward = false;
            } else {
                label.setText("This is the first song in the playlist.");
            }
        } else {
            if (listIterator.hasNext()) {
                label.setText("Now Playing: " + printTitle(listIterator.next().toString()));
                forward = true;
            } else {
                label.setText("End of playlist.");
            }
        }
    }

    private static void removeCurrentSong(JLabel label) {
        if (currentPlaylist.size() > 0) {
            listIterator.remove();
            if (listIterator.hasNext()) {
                label.setText("Now Playing: " + printTitle(listIterator.next().toString()));
            } else {
                if (listIterator.hasPrevious()) {
                    label.setText("Now Playing: " + printTitle(listIterator.previous().toString()));
                } else {
                    label.setText("This playlist is currently empty.");
                }
            }
        }
    }

    private static void showPlaylist() {
        StringBuilder playlistContent = new StringBuilder();
        for (Songs song : currentPlaylist) {
            playlistContent.append(song.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, playlistContent.toString(), "Playlist", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void switchPlaylist(String selectedPlaylist, JLabel label) {
        switch (selectedPlaylist) {
            case "Playlist 1":
                currentPlaylist = playlist1;
                break;
            case "Playlist 2":
                currentPlaylist = playlist2;
                break;
            case "Playlist 3":
                currentPlaylist = playlist3;
                break;
        }
        listIterator = currentPlaylist.listIterator();
        if (currentPlaylist.size() > 0) {
            label.setText("Now Playing: " + printTitle(listIterator.next().toString()));
        } else {
            label.setText("This playlist is currently empty.");
        }
    }
}
