public class LateBindingConfigureActivity extends AutomaticActivity {
	private String invocationID;
	private ArrayList<CandidateService> services = new ArrayList<CandidateService>();
	private String preference;

	protected void executeBusinessLogic(BpelExecution execution) {
		//1 - Enregistre la liste des services pour l'invocation tardive
		Registry.addCandidateServices(invocationID, services);

		//2 - Chargement du modèle de préférences
		LCPnet preferenceModel = LCPnetImpl.load(preference);

		//3 - Configuration de M4ABP (vie une Vue)
		ViewManager vm = fr.orange.monitoring.ViewManager.getViewManager(execution.getProcessInstanceUUID().toString());
		ViewFactory factory = ViewFactory.eINSTANCE;
		DescriptionType viewType = factory.createDescriptionType();
		(...)
		
		List<DimensionType> triplets = viewType.getDimension();
		//3.a - Initialisation de la supervision pour CHAQUE service setup
		for (CandidateService candidateService : services) {
			String contract = candidateService.getContract();
			String operation = candidateService.getOperation();

			//Pour CHAQUE propriété de QoS
			//Les noms des propriétés de QoS sont ceux utilisés dans le LCP-net
			EList<String> properties = preferenceModel.getNodesNames();
			for (String property : properties) {
				DimensionType dimension = factory.createDimensionType();

				dimension.setWsAgreementURL(contract);
				dimension.setServiceName(operation);
				dimension.setProperty(property);

				triplets.add(dimension);
			}
		}

		//3.b - Configuration de la QoI
		Properties props = factory.createProperties();
		viewType.setProperties(props);
		
		Property prop = factory.createProperty();
		prop.setKey("coherency");
		prop.setValue("1000");
		props.getProperty().add(prop);
		(...)
	}
(...)