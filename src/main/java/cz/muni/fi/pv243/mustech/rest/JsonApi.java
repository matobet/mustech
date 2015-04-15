package cz.muni.fi.pv243.mustech.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class JsonApi extends Application {
    public static final String MIME_TYPE = "application/vnd.api+json";
}
