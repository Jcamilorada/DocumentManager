package domain.document;

import lombok.Data;

/**
 * @author Juan Camilo Rada
 */
@Data
public class Document
{
    private String name;
    private String path;
    private String id;
    private String content;
}
