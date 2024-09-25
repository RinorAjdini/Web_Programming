package mk.ukim.finki.wp.lab.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mk.ukim.finki.wp.lab.model.AuthorFullname;

@Converter
public class AuthorFullnameConverter implements AttributeConverter<AuthorFullname, String> {
    private static final String SEPARATOR = ", ";
    @Override
    public String convertToDatabaseColumn(AuthorFullname authorFullname) {

        if(authorFullname == null)
            return null;

        StringBuilder sb = new StringBuilder();
        if(authorFullname.getSurname() != null && !authorFullname.getSurname()
                .isEmpty()){
            sb.append(authorFullname.getSurname());
            sb.append(SEPARATOR);
        }

        if(authorFullname.getName() != null
                && !authorFullname.getName().isEmpty()){
            sb.append(authorFullname.getName());
        }

        return sb.toString();
    }

    @Override
    public AuthorFullname convertToEntityAttribute(String s) {

        if(s == null || s.isEmpty())
            return null;

        String[] parts = s.split(SEPARATOR);

        if(parts == null || parts.length == 0){
            return null;
        }

        AuthorFullname authorFullname = new AuthorFullname();
        String firstPiece = !parts[0].isEmpty() ? parts[0] : null;
        if(s.contains(SEPARATOR)){
            authorFullname.setSurname(firstPiece);

            if(parts.length >=2 && parts[1] != null
                    && !parts[1].isEmpty()){
                authorFullname.setName(parts[1]);
            }
        }else {
            authorFullname.setName(firstPiece);
        }

        return authorFullname;
    }
}
