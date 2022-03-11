package at.ac.fhcampuswien;

public class Article {

    private String author;
    private String title;

    public Article(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }


    /***
     *
     * @return
     */
    @Override
    public String toString() {
        return "Title: " + title + "\t" + "Author: " + author;
    }
}
