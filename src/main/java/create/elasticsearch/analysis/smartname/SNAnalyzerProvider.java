package create.elasticsearch.analysis.smartname;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;

import java.io.IOException;

public class SNAnalyzerProvider extends AbstractIndexAnalyzerProvider<SNAnalyzer> {

    private final SNAnalyzer analyzer;

    /* Constructor. Nothing special here. */
    @Inject
    public SNAnalyzerProvider(IndexSettings indexSettings, Environment environment, String name, Settings settings) throws IOException {
        super(indexSettings, name, settings);
        analyzer = new SNAnalyzer();
        analyzer.setVersion(version);
    }

    /* This function needs to be overridden to return an instance of PlusSignAnalyzer. */
    @Override
    public SNAnalyzer get() {
        return this.analyzer;
    }

}