package kongnoodle.libraryPlatform.demo.feat.book.dto.bookinfoxml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@Getter @Setter
public class Channel {

    @XmlElement(name = "item")
    private List<Item> items;
}