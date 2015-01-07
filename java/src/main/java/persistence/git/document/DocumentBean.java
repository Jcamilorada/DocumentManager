package persistence.git.document;

import lombok.Data;

@Data
public class DocumentBean
{
    private String name;
    private String path;
    private String id;
    private String content;
}
