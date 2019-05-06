package create.elasticsearch.analysis.smartname;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class SNTokenizer extends Tokenizer {

    /* Lucene uses attributes to store information about a single token. For
     * this tokenizer, the only attribute that we are going to use is the
     * CharTermAttribute, which can store the text for the token that is
     * generated. Other types of attributes exist (see interfaces and classes
     * derived from org.apache.lucene.util.Attribute); we will use some of
     * these other attributes when we build our custom token filter. It is
     * important that you register attributes, whatever their type, using the
     * addAttribute() function.
     */
    protected CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);

    /* This is the important function to override from the Tokenizer class. At
     * each call, it should set the value of this.charTermAttribute to the text
     * of the next token. It returns true if a new token is generated and false
     * if there are no more tokens remaining.
     */
    @Override
    public boolean incrementToken() throws IOException {
        if (this.position == 0) {
            String stringToTokenize = getStringToTokenize(this.input);
            this.tokenList = getTokens(stringToTokenize);

            if (this.tokenList.isEmpty()) {
                return false;
            }
        }

        // Clear anything that is already saved in this.charTermAttribute
        this.charTermAttribute.setEmpty();

        if (this.tokenList.size() > this.position) {
            this.charTermAttribute.append(this.tokenList.get(this.position));
            this.position += 1;
            return true;
        } else {
            return false;
        }
    }

    /* This is the constructor for our custom tokenizer class. It takes all
     * information from a java.io.Reader object and stores it in a string. If
     * you are expecting very large blocks of text, you might want to think
     * about using a buffer and saving chunks from the reader whenever
     * incrementToken() is called. This function throws a RuntimeException when
     * an IOException is raised - you can choose how you want to deal with the
     * IOException, but for our purposes, we do not need to try to recover
     * from it.
     */
    public SNTokenizer() {
        super();
    }

    private String getStringToTokenize(Reader reader) {
        int numChars;
        char[] buffer = new char[1024];
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((numChars =
                    reader.read(buffer, 0, buffer.length)) != -1) {
                stringBuilder.append(buffer, 0, numChars);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stringBuilder.toString();
    }

    /* Reset the stored position for this object when reset() is called.
     */
    @Override
    public void reset() throws IOException {
        super.reset();
        this.position = 0;
        this.tokenList.clear();
    }

    /* This object stores the string that we are turning into tokens. We will
     * process its content as we call the incrementToken() function.
     */
    protected String stringToTokenize;

    /* This stores the current position in this.stringToTokenize. We will increment its value as
     * we call the incrementToken() function.
     */
    protected int position = 0;

    protected List<String> tokenList = new ArrayList<>();

    private List<String> getTokens(String inputString) {
        List<String> list = new ArrayList<>();
        if (inputString == null || inputString.isEmpty()) {
            return list;
        }

        String lowercase = inputString.toLowerCase();

        // Remove the string including in ()
        int index = lowercase.indexOf('(');
        if (index > 0) {
            lowercase = lowercase.substring(0, index - 1);
        }
        lowercase = lowercase.trim();

        String[] strs = lowercase.split(" ");
        if (strs.length > 0) {
            list.add(strs[0]);
            String prefix = "";
            String suffix = strs[strs.length - 1];

            for (int i = 0; i < strs.length - 1; i ++ ) {
                if (isNotNullNotEmptyNotWhiteSpace(strs[i])) {
                    prefix += strs[i] + "+";
                    list.add(prefix + suffix);
                }
            }
        }
        return list;
    }

    /**
     * Demonstrate checking for String that is not null, not empty, and not white
     * space only using standard Java classes.
     *
     * @param string String to be checked for not null, not empty, and not white
     *    space only.
     * @return {@code true} if provided String is not null, is not empty, and
     *    has at least one character that is not considered white space.
     */
    private boolean isNotNullNotEmptyNotWhiteSpace (final String string) {
        return string != null && !string.isEmpty() && !string.trim().isEmpty();
    }

}