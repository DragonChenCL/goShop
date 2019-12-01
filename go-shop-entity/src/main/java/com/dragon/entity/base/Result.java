package com.dragon.entity.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private final Integer code;

    private final String message;

    private final T data;

    private Result(Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
    }

    public static<T> Result.Builder<T> builder(){
        return new Result.Builder<>();
    }

    public static class Builder<T>{
        private Integer code;

        private String message;

        private T data;

        public Builder<T> message(String message){
            this.message = message;
            return this;
        }

        public Builder<T> data(T data){
            this.data = data;
            return this;
        }

        public Builder<T> success(){
            this.code = 200;
            return this;
        }

        public Builder<T> fail(){
            this.code = 500;
            return this;
        }

        public Result<T> build(){
            return new Result<T>(this);
        }
    }
}
