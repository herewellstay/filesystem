package com.herewellstay.filesystem;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FilesInDatabase implements Files {
    private FileDAO fileDAO;

    public FilesInDatabase(Database database) {
        fileDAO = database.fileDAO();

    }

    public List<File> all() {
        List<File> result = new ArrayList<>();

        try {
            result = new GetAllAsyncTask(fileDAO).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void add(File file) {
        try {
            new InsertAsyncTask(fileDAO).execute(file).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAll(List<File> others) {
        try {
            new InsertAllAsyncTask(fileDAO).execute(others).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<File> byParent(String parent) {
        List<File> result = new ArrayList<>();

        try {
            result = new GetByParentAsyncTask(fileDAO).execute(parent).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public File byPath(String path) {
        try {
            return new GetFileByPathAsyncTask(fileDAO).execute(path).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<File> byFilter(Filter filter) {
        List<File> result = new ArrayList<>();
        for (File file : all()) {
            if (filter.accepts(file)) {
                result.add(file);
            }
        }

        return result;
    }

    @Override
    public void clear() {
        try {
            new DeleteAllAsyncTask(fileDAO).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean contains(File file) {
        return byPath(file.getPath()) != null;
    }
    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private FileDAO fileDAO;

        DeleteAllAsyncTask(FileDAO dao) {
            fileDAO = dao;
        }

        @Override
        protected Void doInBackground(final Void... params) {

            fileDAO.deleteAll();
            return null;
        }
    }

    private static class GetAllAsyncTask extends AsyncTask<Void, Void, List<File>> {

        private FileDAO fileDAO;

        GetAllAsyncTask(FileDAO dao) {
            fileDAO = dao;
        }

        @Override
        protected List<File> doInBackground(final Void... params) {

            return fileDAO.getAll();
        }
    }

    private static class GetByParentAsyncTask extends AsyncTask<String, Void, List<File>> {

        private FileDAO fileDAO;

        GetByParentAsyncTask(FileDAO dao) {
            fileDAO = dao;
        }

        @Override
        protected List<File> doInBackground(final String... params) {

            return fileDAO.getByParent(params[0]);
        }
    }

    private static class InsertAsyncTask extends AsyncTask<File, Void, Void> {

        private FileDAO fileDAO;

        InsertAsyncTask(FileDAO dao) {
            fileDAO = dao;
        }

        @Override
        protected Void doInBackground(final File... params) {
            fileDAO.insert(params[0]);
            return null;
        }
    }

    private static class InsertAllAsyncTask extends AsyncTask<List<File>, Void, Void> {

        private FileDAO fileDAO;

        InsertAllAsyncTask(FileDAO dao) {
            fileDAO = dao;
        }

        @Override
        protected Void doInBackground(final List<File>... params) {
            fileDAO.insertAll(params[0]);
            return null;
        }
    }

    private static class GetFileByPathAsyncTask extends AsyncTask<String, Void, File> {

        private FileDAO fileDAO;

        GetFileByPathAsyncTask(FileDAO dao) {
            fileDAO = dao;
        }

        @Override
        protected File doInBackground(final String... params) {

            return fileDAO.getByPath(params[0]);
        }
    }

}
