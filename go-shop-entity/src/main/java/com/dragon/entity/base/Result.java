package com.dragon.entity.base;

import lombok.Builder;
import lombok.Data;

@Data
public class Result<T> {
    private Integer code;

    private String message;

    private T data;

    private Result(Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
    }

    public Result.Builder builder(){
        return new Result.Builder<>();
    }

    public static class Builder<T>{
        private Integer code;

        private String message;

        private T data;

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public Builder data(T data){
            this.data = data;
            return this;
        }

        public Builder success(){
            this.code = 200;
            return this;
        }

        public Builder fail(){
            this.code = 500;
            return this;
        }

        public Result build(){
            return new Result<T>(this);
        }
    }
}
