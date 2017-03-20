public class LateBindingInvokeActivity extends AutomaticActivity {
	private String invocationID;
	private String inputVariable;
	private String outputVariable;
	private String preference;

	protected void executeBusinessLogic(BpelExecution execution) {	
		CandidateService[] optimalServices = this.selectOptimalServices(execution);
		this.invokeOptimalService(optimalServices, execution);
	}

	private CandidateService[] selectOptimalServices(BpelExecution execution) throws Exception{
		//1 - On acc�de � la vue d�di�e � cette invocation (gr�ce � invocationID)
		ViewManager vm = ViewManager.getViewManager(execution.getProcessInstanceUUID().toString());
		View view = vm.getView(invocationID);

		//2 - Chargement du mod�le de pr�f�rences
		LCPnet preferenceModel = LCPnetImpl.load(preference);

		//3 - Calcul de l'utilit� globale de chaque service candidat et selection
		ArrayList<CandidateService> optimalServices = new ArrayList<CandidateService>();
		double bestUtilityValue = -1;

		//3.1 - On acc�de � la liste des services pr�c�demment stock�e
		ArrayList<CandidateService> services = Registry.getCandidateServices(invocationID);
		for (CandidateService candidateService : services) {
			//3.2 - On acc�de aux QoS courantes via la vue fournie par M4ABP
			
			//Les noms des propri�t�s de QoS sont ceux utilis�s dans le LCP-net
			EList<String> properties = preferenceModel.getNodesNames()
			BasicEList<Double> propertiesValues = new BasicEList<Double>();
			for (String property : properties) {
				DimensionType dimension = factory.createDimensionType();	
				dimension.setProperty(property);

				//Requ�te de la QoS courante envoy�e � M4ABP
				QoSData data = view.getData(dimension);
				propertiesValues.add(new Double((String)data.getQoSValue()));
			}

			//3.3 - Calcul de l'utilit� globale d'UN service en fonction des ses valeurs courantes de QoS
			double candidateServiceUtility = preferenceModel.getUtility(properties, propertiesValues);

			if(candidateServiceUtility > bestUtilityValue) {
				//C'est le service s�lectionn� (pour l'instant)
				optimalServices = new ArrayList<CandidateService>();
				optimalServices.add(candidateService);
				bestUtilityValue = candidateServiceUtility;	
			} else if(candidateServiceUtility == bestUtilityValue) {
				//Il y a plus d'un service s�lectionn� (utilit�s globales strictement identiques)
				optimalServices.add(candidateService);
			}
		}
		return optimalServices.toArray(new CandidateService[]{});
	}
(...)