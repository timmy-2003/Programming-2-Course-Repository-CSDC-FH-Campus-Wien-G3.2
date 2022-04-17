package at.ac.fhcampuswien;

public class Article {

    private String author;
    private String title;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private String description;

    /***
     * constructor
     * @param author
     * @param title
     * @param url
     * @param urlToImage
     * @param publishedAt
     * @param content
     * @param description
     */
    public Article(String author, String title, String url, String urlToImage, String publishedAt, String content, String description) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.description = description;
    }

    /***
     * constructor
     * @param author
     * @param title
     */
    public Article(String author, String title) {
        this.author = author;
        this.title = title;
    }

    /***
     * get author of article
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /***
     * get title of article
     * @return
     */
    public String getTitle() {
        return title;
    }


    /**
     * @return author and title of article formatted as a String
     */
    @Override
    public String toString() {
        return "Title: " + title + "\t" + "Author: " + author;
    }
}
