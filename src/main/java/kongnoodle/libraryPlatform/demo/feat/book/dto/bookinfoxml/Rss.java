package kongnoodle.libraryPlatform.demo.feat.book.dto.bookinfoxml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rss")
@ToString
@Getter
@Setter
public class Rss {

    @XmlElement(name = "channel")
    private Channel channel;
}