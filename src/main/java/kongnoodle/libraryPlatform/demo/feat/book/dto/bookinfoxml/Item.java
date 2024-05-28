package kongnoodle.libraryPlatform.demo.feat.book.dto.bookinfoxml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@Getter @Setter
public class Item {
    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "image")
    private String image;

    @XmlElement(name = "author")
    private String author;

    @XmlElement(name = "publisher")
    private String publisher;

    @XmlElement(name = "pubdate")
    private String pubdate;

    @XmlElement(name = "description")
    private String description;

}
