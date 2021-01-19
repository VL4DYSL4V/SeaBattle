package converter;

import enums.Rank;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public final class RankToStringConverter implements AttributeConverter<Rank, String> {

    @Override
    public String convertToDatabaseColumn(Rank rank) {
        return rank.getRepresentation();
    }

    @Override
    public Rank convertToEntityAttribute(String s) {
        return Rank.ofRepresentation(s);
    }

}
