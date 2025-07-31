package br.fadesp.desafio.tecnico.pagamento_api.arch_test;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "br.fadesp.desafio.tecnico.pagamento_api")
public class LayeredArchitectureTest {

    private static final String DOMAIN = "Domain";
    private static final String PORTS_IN = "PortsIn";
    private static final String PORTS_OUT = "PortsOut";
    private static final String USE_CASE = "UseCase";
    private static final String ADAPTERS_IN = "AdaptersIn";
    private static final String ADAPTERS_OUT = "AdaptersOut";
    private static final String CONFIG = "Config";

    private static final String BASE_PACKAGE = "br.fadesp.desafio.tecnico.pagamento_api";
    private static final String DOMAIN_PACKAGE = BASE_PACKAGE + ".application.core.domain..";
    private static final String PORTS_IN_PACKAGE = BASE_PACKAGE + ".application.ports.in..";
    private static final String PORTS_OUT_PACKAGE = BASE_PACKAGE + ".application.ports.out..";
    private static final String USE_CASE_PACKAGE = BASE_PACKAGE + ".application.core.usecase..";
    private static final String ADAPTERS_IN_PACKAGE = BASE_PACKAGE + ".adapters.in..";
    private static final String ADAPTERS_OUT_PACKAGE = BASE_PACKAGE + ".adapters.out..";
    private static final String CONFIG_PACKAGE = BASE_PACKAGE + ".config..";


    @ArchTest
    public static final ArchRule hexagonal_layered_architecture_rules = layeredArchitecture()
            .consideringAllDependencies()
            .layer(DOMAIN).definedBy(DOMAIN_PACKAGE)
            .layer(PORTS_IN).definedBy(PORTS_IN_PACKAGE)
            .layer(PORTS_OUT).definedBy(PORTS_OUT_PACKAGE)
            .layer(USE_CASE).definedBy(USE_CASE_PACKAGE)
            .layer(ADAPTERS_IN).definedBy(ADAPTERS_IN_PACKAGE)
            .layer(ADAPTERS_OUT).definedBy(ADAPTERS_OUT_PACKAGE)
            .layer(CONFIG).definedBy(CONFIG_PACKAGE)
            .whereLayer(DOMAIN).mayOnlyBeAccessedByLayers(USE_CASE)
            .whereLayer(PORTS_IN).mayOnlyBeAccessedByLayers(ADAPTERS_IN, USE_CASE)
            .whereLayer(PORTS_OUT).mayOnlyBeAccessedByLayers(ADAPTERS_OUT, USE_CASE)
            .whereLayer(ADAPTERS_IN).mayOnlyBeAccessedByLayers(CONFIG)
            .whereLayer(ADAPTERS_OUT).mayOnlyBeAccessedByLayers(CONFIG)
            .whereLayer(USE_CASE).mayOnlyBeAccessedByLayers(CONFIG)
            .whereLayer(CONFIG).mayNotBeAccessedByAnyLayer();



}
