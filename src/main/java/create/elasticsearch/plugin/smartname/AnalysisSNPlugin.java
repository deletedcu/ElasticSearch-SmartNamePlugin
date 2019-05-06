package create.elasticsearch.plugin.smartname;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.TokenFilterFactory;

import create.elasticsearch.analysis.smartname.SNTokenFilterFactory;
import create.elasticsearch.analysis.smartname.SNTokenizerFactory;

import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.Collections;
import java.util.Map;

public class AnalysisSNPlugin extends Plugin implements AnalysisPlugin {
    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
        return Collections.singletonMap("smart_name_filter", SNTokenFilterFactory::new);
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
        return Collections.singletonMap("smart_name_tokenizer", SNTokenizerFactory::new);
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        return Collections.singletonMap("smart_name", create.elasticsearch.analysis.smartname.SNAnalyzerProvider::new);
    }
}
