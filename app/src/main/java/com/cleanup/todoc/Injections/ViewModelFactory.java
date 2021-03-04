package com.cleanup.todoc.Injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.viewmodel.TaskViewModel;

import java.util.concurrent.Executor;

/**
 * todoc-master
 * Created by LioNDeVLaB on 04/03/2021
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

	private final TaskDataRepository mTaskDataSource;
	private final ProjectDataRepository mProjectDataSource;
	private final Executor mExecutor;

	public ViewModelFactory(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
		this.mTaskDataSource = taskDataSource;
		this.mProjectDataSource = projectDataSource;
		this.mExecutor = executor;
	}

	@Override
	public <T extends ViewModel> T create(Class<T> modelClass) {
		if (modelClass.isAssignableFrom(TaskViewModel.class)) {
			return (T) new TaskViewModel(mTaskDataSource, mProjectDataSource, mExecutor);
		}
		throw new IllegalArgumentException("Unknown ViewModel class");
	}
}
