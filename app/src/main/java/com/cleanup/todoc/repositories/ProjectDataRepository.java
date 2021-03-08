package com.cleanup.todoc.repositories;



import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

/**
 * todoc-master
 * Created by LioNDeVLaB on 04/03/2021
 */
public class ProjectDataRepository {

	private final ProjectDao mProjectDao;

	public ProjectDataRepository(ProjectDao projectDao) {
		this.mProjectDao = projectDao;
	}

	// --- GET PROJECT ---
	public LiveData<List<Project>> getProjects() {
		return this.mProjectDao.getAllProjects();
	}
}
