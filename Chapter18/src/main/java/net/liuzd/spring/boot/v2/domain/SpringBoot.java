package net.liuzd.spring.boot.v2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpringBoot {

    private String author;

    private String title;

    private String url;

}
