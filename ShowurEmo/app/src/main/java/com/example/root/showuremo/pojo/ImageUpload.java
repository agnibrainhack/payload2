package com.example.root.showuremo.pojo;

/**
 * Created by hp on 28-01-2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageUpload {



        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("image_result")
        @Expose
        private String imageResult;
        @SerializedName("resource_uri")
        @Expose
        private String resourceUri;
        @SerializedName("time")
        @Expose
        private String time;

        /**
         * No args constructor for use in serialization
         *
         */
        public ImageUpload() {
        }

        /**
         *
         * @param id
         * @param time
         * @param createdAt
         * @param image
         * @param imageResult
         * @param resourceUri
         */
        public ImageUpload(String createdAt, Integer id, String image, String imageResult, String resourceUri, String time) {
            super();
            this.createdAt = createdAt;
            this.id = id;
            this.image = image;
            this.imageResult = imageResult;
            this.resourceUri = resourceUri;
            this.time = time;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImageResult() {
            return imageResult;
        }

        public void setImageResult(String imageResult) {
            this.imageResult = imageResult;
        }

        public String getResourceUri() {
            return resourceUri;
        }

        public void setResourceUri(String resourceUri) {
            this.resourceUri = resourceUri;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

}

