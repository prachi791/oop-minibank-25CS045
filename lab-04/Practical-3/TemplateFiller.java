import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateFiller {

    public static String fillTemplate(
            String template, String[] names, String[] values) {

        Pattern pattern = Pattern.compile("\\{(\\w+)\\}");
        Matcher matcher = pattern.matcher(template);

        StringBuilder result = new StringBuilder();
        int lastPosition = 0;

        while (matcher.find()) {
            result.append(template, lastPosition, matcher.start());

            String placeholder = matcher.group(1);
            String replacement = "[?]";

            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(placeholder)) {
                    replacement = values[i];
                    break;
                }
            }

            result.append(replacement);
            lastPosition = matcher.end();
        }

        result.append(template.substring(lastPosition));

        return result.toString();
    }
}
