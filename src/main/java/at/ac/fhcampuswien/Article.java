package at.ac.fhcampuswien;
//uses builder pattern
//example of creating an article object now
//new Article.Builder("author", "title).build(); //author and title always have to be given in the builder constructor
                                                // meaning there always has to be an author and title
//if you want to add other attributes beside author and title, you can do so as following:
//new Article.Builder("author", "title").url("https://www.url.com").content("loremipsum").publishedAt("01.01.2001").build();
//given attributes, beside author and title, dont have to be given to the builder in any specific order
public class Article {
    private String author;
    private String title;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private String description;
    private Source source;

    //old constructor ( telescopic constructor )
    /*
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
     */

    //new constructor, uses builder pattern
    private Article(Builder builder) { //private constructor
        //author and title dont have to be checked as they are a requirement even in the builder class
        this.author = builder.author;
        this.title = builder.title;
        if(builder.url!=null){this.url = builder.url;}
        if(builder.urlToImage!=null){this.urlToImage = builder.urlToImage;}
        if(builder.publishedAt!=null) {this.publishedAt = builder.publishedAt;}
        if(builder.content!=null) {this.content = builder.content;}
        if(builder.description!=null) {this.description = builder.description;}
        if(builder.source!=null) {this.source = builder.source;}
    }

    public static class Builder{
        private String author;
        private String title;
        private String url;
        private String urlToImage;
        private String publishedAt;
        private String content;
        private String description;
        private Source source;


        public Builder(String author, String title){
            this.author = author;
            this.title = title;
        }

        public Article build() {
            return new Article(this);
        } //build method for creation of object

        public Builder author(String value){author = value;return this;}

        public Builder title(String value){title = value;return this;}

        public Builder url(String value){url = value;return this;}

        public Builder urlToImage(String value){urlToImage = value;return this;}

        public Builder publishedAt(String value){publishedAt = value;return this;}

        public Builder content(String value){content = value;return this;}

        public Builder description(String value){description = value;return this;}

        public Builder source(Source value){source = value;return this;}
    }

    /*
    //old constructors
    public Article(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Article(String author, String title, String desc) {
        this.author = author;
        this.title = title;
        this.description= desc;
    }

    public Article(String author, String title, Source source) {
        this.author = author;
        this.title = title;
        this.source = source;
    }
     */

    //GETTERS
    public String getAuthor() {return author;}

    public String getTitle() {return title;}

    public String getDescription() {return description;}

    public int getLength() {return description.length();}

    public Source getSource() {return source;}

    public String getSourceName() {return source.getName();}

    public String getUrlToImage() {return urlToImage;}

    /***
     * get title of article
     * @return
     */
    public String getUrl() {
        return url;
    }

    //SETTERS
    public void setDescription(String description) {this.description = description;}

    public void setAuthor(String author) {this.author = author;}

    /**
     * @return author and title of article formatted as a String
     */
    @Override
    public String toString() {
        return "Title: " + title + "\t" + "Author: " + author + "\t" +  "Description: " + description;
    }
}
