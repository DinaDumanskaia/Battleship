abstract class BaseEntity {

    protected long id;

    protected long version;

    public BaseEntity(long id, long version) {
        this.id = id;
        this.version = version;
    }

   public BaseEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}

class User extends BaseEntity {

    protected String name;

//    public User(long id, long version, String name) {
//        super(id, version);
//        this.name = name;
//    }

    public User() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Visit extends BaseEntity {

    protected User user;

    protected WebSite site;

    public Visit(long id, long version, User user, WebSite site) {
        super(id, version);
        this.user = user;
        this.site = site;
    }

    public Visit() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WebSite getSite() {
        return site;
    }

    public void setSite(WebSite site) {
        this.site = site;
    }
}

class WebSite extends BaseEntity {

    protected String url;

    public WebSite(long id, long version, String url) {
        super(id, version);
        this.url = url;
    }

    public WebSite() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}