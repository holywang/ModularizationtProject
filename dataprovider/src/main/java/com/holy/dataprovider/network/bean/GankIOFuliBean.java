package com.holy.dataprovider.network.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by holywang on 2018/7/10.
 */


public class GankIOFuliBean {

    private boolean error;
    private List<Results> results;
    public void setError(boolean error) {
        this.error = error;
    }
    public boolean getError() {
        return error;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
    public List<Results> getResults() {
        return results;
    }


    public class Results {

        private String Id;
        private Date createdat;
        private Date desc;
        private Date publishedat;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public void setId(String Id) {
            this.Id = Id;
        }
        public String getId() {
            return Id;
        }

        public void setCreatedat(Date createdat) {
            this.createdat = createdat;
        }
        public Date getCreatedat() {
            return createdat;
        }

        public void setDesc(Date desc) {
            this.desc = desc;
        }
        public Date getDesc() {
            return desc;
        }

        public void setPublishedat(Date publishedat) {
            this.publishedat = publishedat;
        }
        public Date getPublishedat() {
            return publishedat;
        }

        public void setSource(String source) {
            this.source = source;
        }
        public String getSource() {
            return source;
        }

        public void setType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        public String getUrl() {
            return url;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }
        public boolean getUsed() {
            return used;
        }

        public void setWho(String who) {
            this.who = who;
        }
        public String getWho() {
            return who;
        }

    }

}
