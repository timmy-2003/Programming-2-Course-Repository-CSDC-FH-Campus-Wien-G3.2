package at.ac.fhcampuswien;
//Uses Buidler Pattern
public class Article {
    private String author;
    private String title;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private String description;
    private Source source;

    public static class ArticleBuilder{
        private String author;
        private String title;
        private String url;
        private String urlToImage;
        private String publishedAt;
        private String content;
        private String description;
        private Source source;

        public ArticleBuilder(String author, String title, String url, String urlToImage,
                              String publishedAt, String content, String description, Source source){
            this.author = author;
            this.title = title;
            this.url = url;
            this.urlToImage = urlToImage;
            this.publishedAt = publishedAt;
            this.content = content;
            this.description = description;
            this.source = source;
        }

        public Article build() {
            return new Article(this);
            /*
            if(author == null || title == null){
                throw new IllegalStateException("Article is missing one or multiple attributes!");

            }
            if(url == null){
                throw new IllegalStateException("Article is missing an attribute!");
            }
            if(urlToImage == null){
                throw new IllegalStateException("Article is missing an attribute!");
            }
            if(publishedAt == null){
                throw new IllegalStateException("Article is missing an attribute!");
            }
            if(content == null){
                throw new IllegalStateException("Article is missing an attribute!");
            }
            if(description == null){
                throw new IllegalStateException("Article is missing an attribute!");
            }
            if(source == null){
                throw new IllegalStateException("Article is missing an attribute!");
            }*/
        }

        public ArticleBuilder author(String value){
            author = value;
            return this;
        }

        public ArticleBuilder title(String value){
            title = value;
            return this;
        }

        public ArticleBuilder url(String value){
            url = value;
            return this;
        }

        public ArticleBuilder urlToImage(String value){
            urlToImage = value;
            return this;
        }

        public ArticleBuilder publishedAt(String value){
            publishedAt = value;
            return this;
        }

        public ArticleBuilder content(String value){
            content = value;
            return this;
        }

        public ArticleBuilder description(String value){
            description = value;
            return this;
        }

        public ArticleBuilder source(Source value){
            source = value;
            return this;
        }
    }

    //CONSTRUCTOR
    /*
    private Article(String author, String title, String url, String urlToImage, String publishedAt, String content, String description, Source source) {
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
    private Article(ArticleBuilder articleBuilder) {
        this.author = articleBuilder.author;
        this.title = articleBuilder.title;
        this.url = articleBuilder.url;
        this.urlToImage = articleBuilder.urlToImage;
        this.publishedAt = articleBuilder.publishedAt;
        this.content = articleBuilder.content;
        this.description = articleBuilder.description;
        this.source = articleBuilder.source;
    }

    //CONSTRUCTOR
    private Article(String author, String title) {
        this.author = author;
        this.title = title;
    }

    //CONSTRUCTOR
    private Article(String author, String title, String desc) {
        this.author = author;
        this.title = title;
        this.description= desc;
    }

    //CONSTRUCTOR
    private Article(String author, String title, Source source) {
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

    public int getLength()
    {
        return description.length();
    }

    /***
     * get source of article
     * @return
     */
    public Source getSource() {
        return source;
    }

    public String getSourceName() {
        return source.getName();
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
