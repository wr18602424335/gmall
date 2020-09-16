package com.aryun.gmall.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = APP.ESProp.INDEX_NAME, type = APP.ESProp.TYPE_TASK_INFO)
    public class TaskInfo  {
        @Id
        @Field(index = false, store = true)
        private String taskId;

        @Field(type = FieldType.Integer, store = true)
        private Integer userId;

        @Field(type = FieldType.Text,store = true)
        private String taskContent;

        @Field(type = FieldType.Text, analyzer="ik_max_word",searchAnalyzer="ik_max_word", store = true)
        private String taskArea;

        @Field(type = FieldType.Text, store = true)
        private String taskTags;

        @Field(type = FieldType.Text, index = false, store = true)
        private String taskState;

        @Field(type = FieldType.Text, index = false, store = true)
        private String updateTime;

        @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", store = true)
        private String userNickName;

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getTaskContent() {
            return taskContent;
        }

        public void setTaskContent(String taskContent) {
            this.taskContent = taskContent;
        }

        public String getTaskArea() {
            return taskArea;
        }

        public void setTaskArea(String taskArea) {
            this.taskArea = taskArea;
        }

        public String getTaskTags() {
            return taskTags;
        }

        public void setTaskTags(String taskTags) {
            this.taskTags = taskTags;
        }

        public String getTaskState() {
            return taskState;
        }

        public void setTaskState(String taskState) {
            this.taskState = taskState;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUserNickName() {
            return userNickName;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }

        @Override
        public String toString() {
            return "TaskInfo [taskId=" + taskId + ", userId=" + userId
                    + ", taskContent=" + taskContent + ", taskArea=" + taskArea
                    + ", taskState=" + taskState
                    + ", updateTime=" + updateTime + ", userNickName="
                    + userNickName + "]";
        }

        public TaskInfo(String taskId, Integer userId, String taskContent,
                        String taskArea, String taskTags, String taskState,
                        String updateTime, String userNickName) {
            this.taskId = taskId;
            this.userId = userId;
            this.taskContent = taskContent;
            this.taskArea = taskArea;
            this.taskTags = taskTags;
            this.taskState = taskState;
            this.updateTime = updateTime;
            this.userNickName = userNickName;
        }

        public TaskInfo() {
            // TODO Auto-generated constructor stub
        }

}
