package com.toto.tdng.datachecker.service;

import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.toto.tdng.datachecker.common.model.CheckDM;
import com.toto.tdng.datachecker.common.model.WorkerOutput;
import com.toto.tdng.datachecker.common.model.WorkerStatus;

@ApplicationScoped
public class CheckPackage {

	public CheckDM addToCheckDMResults(CheckDM check, WorkerOutput workerOutput) {
		check.getWorkerOutputs().add(workerOutput);

		//Computation expensive log that is only used for debugging
		// List<WorkerType> workerPresent = check.getWorkerOutputs().stream()
		// 		.map(WorkerOutput::getWorkerType)
		// 		.collect(Collectors.toList());
		// ArrayList<WorkerType> missingChecks = new ArrayList<WorkerType>(check.getTasks());
		// missingChecks.removeAll(workerPresent);
		// log.info("Received " + workerOutput.getWorkerType() + " (" + check.getWorkerOutputs().size() + "/"
		// 		+ check.getTasks().size() + ") Missing : " + missingChecks);

		// log.info("Received " + workerOutput.getWorkerType() + " (" + check.getWorkerOutputs().size() + "/"
		// 		+ check.getTasks().size() + ")");

		return check;
	}

	public Boolean verifyWorkerOutput(WorkerOutput workerOutput, CheckDM checkDM) {
		Boolean expected = checkDM.getTasks().contains(workerOutput.getWorkerType());
		Boolean duplicate = checkDM.getWorkerOutputs().stream().map(o -> o.getWorkerType()).collect(Collectors.toList())
				.contains(workerOutput.getWorkerType());
		return expected && !duplicate;
	}

	public WorkerStatus generateWorkerStatus(WorkerOutput workerOutput, CheckDM checkDM) {
		return new WorkerStatus(checkDM.getDeliveryId(), checkDM.getFilename(), workerOutput.getWorkerType(),
				workerOutput.getStatus());
	}

}
