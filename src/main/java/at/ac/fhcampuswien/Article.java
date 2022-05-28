package at.ac.fhcampuswien;

public class Article {

    private String author;
    private String title;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    private String description;
    private Source source;

    /***
     * constructor
     * @param author
     * @param title
     * @param url
     * @param urlToImage
     * @param publishedAt
     * @param content
     * @param description
     * @param source
     */
    public Article(String author, String title, String url, String urlToImage, String publishedAt, String content, String description, Source source) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.description = description;
        this.source = source;
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
     * constructor
     * @param author
     * @param title
     * @param desc
     */
    public Article(String author, String title, String desc) {
        this.author = author;
        this.title = title;
        this.description= desc;
    }

    /***
     * constructor
     * @param author
     * @param title
     * @param source
     */
    public Article(String author, String title, Source source) {
        this.author = author;
        this.title = title;
        this.source = source;
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


    /***
     * get desc of article
     * @return
     */
    public String getDescription() {
        return description;
    }

    /***
     * get source of article
     * @return
     */
    public Source getSource() {
        return source;
    }

    /***
     * set desc of article
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /***
     * set author of article
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return author and title of article formatted as a String
     */
    @Override
    public String toString() {
        return "Title: " + title + "\t" + "Author: " + author + "\t" +  "Description: " + description;
    }
}
