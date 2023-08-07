package edu.ksu.canvas;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.ksu.canvas.impl.AccountImpl;
import edu.ksu.canvas.impl.AccountReportImpl;
import edu.ksu.canvas.impl.AccountReportSummaryImpl;
import edu.ksu.canvas.impl.AdminImpl;
import edu.ksu.canvas.impl.AssignmentGroupImpl;
import edu.ksu.canvas.impl.AssignmentImpl;
import edu.ksu.canvas.impl.AssignmentOverrideImpl;
import edu.ksu.canvas.impl.AuthenticationLogImpl;
import edu.ksu.canvas.impl.BaseImpl;
import edu.ksu.canvas.impl.CalendarEventImpl;
import edu.ksu.canvas.impl.CommunicationChannelImpl;
import edu.ksu.canvas.impl.ContentMigrationImpl;
import edu.ksu.canvas.impl.ConversationImpl;
import edu.ksu.canvas.impl.CourseImpl;
import edu.ksu.canvas.impl.CourseSettingsImpl;
import edu.ksu.canvas.impl.EnrollmentImpl;
import edu.ksu.canvas.impl.EnrollmentTermImpl;
import edu.ksu.canvas.impl.ExternalToolImpl;
import edu.ksu.canvas.impl.FeatureFlagImpl;
import edu.ksu.canvas.impl.FeatureImpl;
import edu.ksu.canvas.impl.FileImpl;
import edu.ksu.canvas.impl.GradingStandardImpl;
import edu.ksu.canvas.impl.LoginImpl;
import edu.ksu.canvas.impl.MigrationIssueImpl;
import edu.ksu.canvas.impl.ModuleImpl;
import edu.ksu.canvas.impl.PageImpl;
import edu.ksu.canvas.impl.ProgressImpl;
import edu.ksu.canvas.impl.QuizImpl;
import edu.ksu.canvas.impl.QuizQuestionImpl;
import edu.ksu.canvas.impl.QuizSubmissionImpl;
import edu.ksu.canvas.impl.QuizSubmissionQuestionImpl;
import edu.ksu.canvas.impl.RoleImpl;
import edu.ksu.canvas.impl.RubricImpl;
import edu.ksu.canvas.impl.SectionsImpl;
import edu.ksu.canvas.impl.SelectiveDataImpl;
import edu.ksu.canvas.impl.SisImportImpl;
import edu.ksu.canvas.impl.SubmissionImpl;
import edu.ksu.canvas.impl.TabImpl;
import edu.ksu.canvas.impl.UserImpl;
import edu.ksu.canvas.interfaces.AccountReader;
import edu.ksu.canvas.interfaces.AccountReportReader;
import edu.ksu.canvas.interfaces.AccountReportSummaryReader;
import edu.ksu.canvas.interfaces.AccountReportSummaryWriter;
import edu.ksu.canvas.interfaces.AccountReportWriter;
import edu.ksu.canvas.interfaces.AccountWriter;
import edu.ksu.canvas.interfaces.AdminReader;
import edu.ksu.canvas.interfaces.AdminWriter;
import edu.ksu.canvas.interfaces.AssignmentGroupReader;
import edu.ksu.canvas.interfaces.AssignmentGroupWriter;
import edu.ksu.canvas.interfaces.AssignmentOverrideReader;
import edu.ksu.canvas.interfaces.AssignmentOverrideWriter;
import edu.ksu.canvas.interfaces.AssignmentReader;
import edu.ksu.canvas.interfaces.AssignmentWriter;
import edu.ksu.canvas.interfaces.AuthenticationLogReader;
import edu.ksu.canvas.interfaces.CalendarReader;
import edu.ksu.canvas.interfaces.CalendarWriter;
import edu.ksu.canvas.interfaces.CanvasReader;
import edu.ksu.canvas.interfaces.CanvasWriter;
import edu.ksu.canvas.interfaces.CommunicationChannelReader;
import edu.ksu.canvas.interfaces.CommunicationChannelWriter;
import edu.ksu.canvas.interfaces.ContentMigrationReader;
import edu.ksu.canvas.interfaces.ContentMigrationWriter;
import edu.ksu.canvas.interfaces.ConversationReader;
import edu.ksu.canvas.interfaces.ConversationWriter;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.interfaces.CourseSettingsReader;
import edu.ksu.canvas.interfaces.CourseSettingsWriter;
import edu.ksu.canvas.interfaces.CourseWriter;
import edu.ksu.canvas.interfaces.EnrollmentReader;
import edu.ksu.canvas.interfaces.EnrollmentTermReader;
import edu.ksu.canvas.interfaces.EnrollmentWriter;
import edu.ksu.canvas.interfaces.ExternalToolReader;
import edu.ksu.canvas.interfaces.ExternalToolWriter;
import edu.ksu.canvas.interfaces.FeatureFlagReader;
import edu.ksu.canvas.interfaces.FeatureFlagWriter;
import edu.ksu.canvas.interfaces.FeatureReader;
import edu.ksu.canvas.interfaces.FileReader;
import edu.ksu.canvas.interfaces.FileWriter;
import edu.ksu.canvas.interfaces.GradingStandardReader;
import edu.ksu.canvas.interfaces.GradingStandardWriter;
import edu.ksu.canvas.interfaces.LoginReader;
import edu.ksu.canvas.interfaces.LoginWriter;
import edu.ksu.canvas.interfaces.MigrationIssueReader;
import edu.ksu.canvas.interfaces.ModuleReader;
import edu.ksu.canvas.interfaces.PageReader;
import edu.ksu.canvas.interfaces.PageWriter;
import edu.ksu.canvas.interfaces.ProgressReader;
import edu.ksu.canvas.interfaces.ProgressWriter;
import edu.ksu.canvas.interfaces.QuizQuestionReader;
import edu.ksu.canvas.interfaces.QuizQuestionWriter;
import edu.ksu.canvas.interfaces.QuizReader;
import edu.ksu.canvas.interfaces.QuizSubmissionQuestionReader;
import edu.ksu.canvas.interfaces.QuizSubmissionQuestionWriter;
import edu.ksu.canvas.interfaces.QuizSubmissionReader;
import edu.ksu.canvas.interfaces.QuizSubmissionWriter;
import edu.ksu.canvas.interfaces.QuizWriter;
import edu.ksu.canvas.interfaces.RoleReader;
import edu.ksu.canvas.interfaces.RoleWriter;
import edu.ksu.canvas.interfaces.RubricReader;
import edu.ksu.canvas.interfaces.RubricWriter;
import edu.ksu.canvas.interfaces.SectionReader;
import edu.ksu.canvas.interfaces.SectionWriter;
import edu.ksu.canvas.interfaces.SelectiveDataReader;
import edu.ksu.canvas.interfaces.SisImportReader;
import edu.ksu.canvas.interfaces.SisImportWriter;
import edu.ksu.canvas.interfaces.SubmissionReader;
import edu.ksu.canvas.interfaces.SubmissionWriter;
import edu.ksu.canvas.interfaces.TabReader;
import edu.ksu.canvas.interfaces.TabWriter;
import edu.ksu.canvas.interfaces.UserReader;
import edu.ksu.canvas.interfaces.UserWriter;
import edu.ksu.canvas.net.RefreshingRestClient;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

/**
 * Entry point for using the Canvas API library. It constructs concrete
 * implementations of reader/writer classes to perform API calls with.
 * It must be constructed with a Canvas instance URL which will be used
 * for all API calls. It has options to specify network timeouts and a way
 * to control API pagination.
 */
public class CanvasApiFactory {

    public static final Integer CANVAS_API_VERSION = 1;
    private static final Logger LOG = LoggerFactory.getLogger(CanvasApiFactory.class);
    private static final int DEFAULT_CONNECT_TIMEOUT_MS = 5000;
    private static final int DEFAULT_READ_TIMEOUT_MS = 120000;
    Map<Class<? extends CanvasReader>, Class<? extends BaseImpl>> readerMap;
    Map<Class<? extends CanvasWriter>, Class<? extends BaseImpl>> writerMap;
    private String canvasBaseUrl;
    private int connectTimeout;
    private int readTimeout;

    /**
     * Construct an API factory for a given instance of Canvas.
     * @param canvasBaseUrl The base URL used to access your Canvas instance
     */
    public CanvasApiFactory(String canvasBaseUrl) {
        LOG.debug("Creating Canvas API factory with base URL: {}", canvasBaseUrl);
        this.canvasBaseUrl = canvasBaseUrl;
        this.connectTimeout = DEFAULT_CONNECT_TIMEOUT_MS;
        this.readTimeout = DEFAULT_READ_TIMEOUT_MS;
        setupClassMap();
    }

    /**
     * Construct an API factory with specified timeout values
     * @param canvasBaseUrl The base URL used to access your Canvas instance
     * @param connectTimeout Connection timeout in milliseconds
     * @param readTimeout Read timeout in milliseconds. If this is too low, longer API queries could time out prematurely
     */
    public CanvasApiFactory(String canvasBaseUrl, int connectTimeout, int readTimeout) {
        LOG.debug("Creating Canvas API factory with base URL: {}, connect timeout: {}, read timeout: {}", canvasBaseUrl, connectTimeout, readTimeout);
        this.canvasBaseUrl = canvasBaseUrl;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        setupClassMap();
    }

    /**
     * Get a reader implementation class to perform API calls with.
     * @param type Interface type you wish to get an implementation for
     * @param oauthToken An OAuth token to use for authentication when making API calls
     * @param <T> The reader type to request an instance of
     * @return A reader implementation class
     */
    public <T extends CanvasReader> T getReader(Class<T> type, OauthToken oauthToken) {
        return getReader(type, oauthToken, null);
    }

    /**
     * Get a reader implementation class to perform API calls with while specifying
     * an explicit page size for paginated API calls. This gets translated to a per_page=
     * parameter on API requests. Note that Canvas does not guarantee it will honor this page size request.
     * There is an explicit maximum page size on the server side which could change. The default page size
     * is 10 which can be limiting when, for example, trying to get all users in a 800 person course.
     * @param type Interface type you wish to get an implementation for
     * @param oauthToken An OAuth token to use for authentication when making API calls
     * @param paginationPageSize Requested pagination page size
     * @param <T> The reader type to request an instance of
     * @return An instance of the requested reader class
     */
    public <T extends CanvasReader> T getReader(Class<T> type, OauthToken oauthToken, Integer paginationPageSize) {
        LOG.debug("Factory call to instantiate reader class: {}", type.getName());
        RestClient restClient = new RefreshingRestClient();

        @SuppressWarnings("unchecked")
        Class<T> concreteClass = (Class<T>)readerMap.get(type);

        if (concreteClass == null) {
            throw new UnsupportedOperationException("No implementation for requested interface found: " + type.getName());
        }

        LOG.debug("got class: {}", concreteClass);
        try {
            Constructor<T> constructor = concreteClass.getConstructor(String.class, Integer.class,
                    OauthToken.class, RestClient.class, Integer.TYPE, Integer.TYPE, Integer.class, Boolean.class);
            return constructor.newInstance(canvasBaseUrl, CANVAS_API_VERSION, oauthToken, restClient,
                    connectTimeout, readTimeout, paginationPageSize, false);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new UnsupportedOperationException("Unknown error instantiating the concrete API class: " + type.getName(), e);
        }
    }

    /**
     * Get a writer implementation to push data into Canvas.
     * @param type Interface type you wish to get an implementation for
     * @param oauthToken An OAuth token to use for authentication when making API calls
     * @param <T> A writer implementation
     * @return A writer implementation class
     */
    public <T extends CanvasWriter> T getWriter(Class<T> type, OauthToken oauthToken) {
        return getWriter(type, oauthToken, false);
    }

    /**
     * Get a writer implementation to push data into Canvas while being able to control the behavior of blank values.
     * If the serializeNulls parameter is set to true, this writer will serialize null fields in the JSON being
     * sent to Canvas. This is required if you want to explicitly blank out a value that is currently set to something.
     * @param type Interface type you wish to get an implementation for
     * @param oauthToken An OAuth token to use for authentication when making API calls
     * @param serializeNulls Whether or not to include null fields in the serialized JSON. Defaults to false if null
     * @param <T> A writer implementation
     * @return An instantiated instance of the requested writer type
     */
    public <T extends CanvasWriter> T getWriter(Class<T> type, OauthToken oauthToken, Boolean serializeNulls) {
        LOG.debug("Factory call to instantiate writer class: {}", type.getName());
        RestClient restClient = new RefreshingRestClient();

        @SuppressWarnings("unchecked")
        Class<T> concreteClass = (Class<T>) writerMap.get(type);

        if (concreteClass == null) {
            throw new UnsupportedOperationException("No implementation for requested interface found: " + type.getName());
        }

        LOG.debug("got writer class: {}", concreteClass);
        try {
            Constructor<T> constructor = concreteClass.getConstructor(String.class, Integer.class, OauthToken.class,
                    RestClient.class, Integer.TYPE, Integer.TYPE, Integer.class, Boolean.class);
            return constructor.newInstance(canvasBaseUrl, CANVAS_API_VERSION, oauthToken, restClient,
                    connectTimeout, readTimeout, null, serializeNulls);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new UnsupportedOperationException("Unknown error instantiating the concrete API class: " + type.getName(), e);
        }
    }

    private void setupClassMap() {
        readerMap = new HashMap<>();
        writerMap = new HashMap<>();
        readerMap.put(AccountReader.class, AccountImpl.class);
        readerMap.put(AdminReader.class, AdminImpl.class);
        readerMap.put(AssignmentOverrideReader.class, AssignmentOverrideImpl.class);
        readerMap.put(AssignmentReader.class, AssignmentImpl.class);
        readerMap.put(ConversationReader.class, ConversationImpl.class);
        readerMap.put(CourseReader.class, CourseImpl.class);
        readerMap.put(TabReader.class, TabImpl.class);
        readerMap.put(EnrollmentReader.class, EnrollmentImpl.class);
        readerMap.put(QuizQuestionReader.class, QuizQuestionImpl.class);
        readerMap.put(QuizReader.class, QuizImpl.class);
        readerMap.put(QuizSubmissionQuestionReader.class, QuizSubmissionQuestionImpl.class);
        readerMap.put(QuizSubmissionReader.class, QuizSubmissionImpl.class);
        readerMap.put(SectionReader.class, SectionsImpl.class);
        readerMap.put(UserReader.class, UserImpl.class);
        readerMap.put(PageReader.class, PageImpl.class);
        readerMap.put(EnrollmentTermReader.class, EnrollmentTermImpl.class);
        readerMap.put(SubmissionReader.class, SubmissionImpl.class);
        readerMap.put(AssignmentGroupReader.class, AssignmentGroupImpl.class);
        readerMap.put(RoleReader.class, RoleImpl.class);
        readerMap.put(ExternalToolReader.class, ExternalToolImpl.class);
        readerMap.put(FileReader.class, FileImpl.class);
        readerMap.put(LoginReader.class, LoginImpl.class);
        readerMap.put(CalendarReader.class, CalendarEventImpl.class);
        readerMap.put(AccountReportSummaryReader.class, AccountReportSummaryImpl.class);
        readerMap.put(AccountReportReader.class, AccountReportImpl.class);
        readerMap.put(ContentMigrationReader.class, ContentMigrationImpl.class);
        readerMap.put(ProgressReader.class, ProgressImpl.class);
        readerMap.put(CourseSettingsReader.class, CourseSettingsImpl.class);
        readerMap.put(GradingStandardReader.class, GradingStandardImpl.class);
        readerMap.put(ModuleReader.class, ModuleImpl.class);
        readerMap.put(SisImportReader.class, SisImportImpl.class);
        readerMap.put(SelectiveDataReader.class, SelectiveDataImpl.class);
        readerMap.put(MigrationIssueReader.class, MigrationIssueImpl.class);
        readerMap.put(CommunicationChannelReader.class, CommunicationChannelImpl.class);
        readerMap.put(AuthenticationLogReader.class, AuthenticationLogImpl.class);
        readerMap.put(FeatureReader.class, FeatureImpl.class);
        readerMap.put(FeatureFlagReader.class, FeatureFlagImpl.class);
        readerMap.put(RubricReader.class, RubricImpl.class);

        writerMap.put(AccountWriter.class, AccountImpl.class);
        writerMap.put(AssignmentOverrideWriter.class, AssignmentOverrideImpl.class);
        writerMap.put(AdminWriter.class, AdminImpl.class);
        writerMap.put(AssignmentWriter.class, AssignmentImpl.class);
        writerMap.put(ConversationWriter.class, ConversationImpl.class);
        writerMap.put(CourseWriter.class, CourseImpl.class);
        writerMap.put(TabWriter.class, TabImpl.class);
        writerMap.put(FileWriter.class, FileImpl.class);
        writerMap.put(EnrollmentWriter.class, EnrollmentImpl.class);
        writerMap.put(QuizQuestionWriter.class, QuizQuestionImpl.class);
        writerMap.put(QuizWriter.class, QuizImpl.class);
        writerMap.put(QuizSubmissionQuestionWriter.class, QuizSubmissionQuestionImpl.class);
        writerMap.put(QuizSubmissionWriter.class, QuizSubmissionImpl.class);
        writerMap.put(UserWriter.class, UserImpl.class);
        writerMap.put(PageWriter.class, PageImpl.class);
        writerMap.put(SectionWriter.class, SectionsImpl.class);
        writerMap.put(SubmissionWriter.class, SubmissionImpl.class);
        writerMap.put(AssignmentGroupWriter.class, AssignmentGroupImpl.class);
        writerMap.put(RoleWriter.class, RoleImpl.class);
        writerMap.put(ExternalToolWriter.class, ExternalToolImpl.class);
        writerMap.put(LoginWriter.class, LoginImpl.class);
        writerMap.put(CalendarWriter.class, CalendarEventImpl.class);
        writerMap.put(AccountReportSummaryWriter.class, AccountReportSummaryImpl.class);
        writerMap.put(AccountReportWriter.class, AccountReportImpl.class);
        writerMap.put(ContentMigrationWriter.class, ContentMigrationImpl.class);
        writerMap.put(ProgressWriter.class, ProgressImpl.class);
        writerMap.put(CourseSettingsWriter.class, CourseSettingsImpl.class);
        writerMap.put(GradingStandardWriter.class, GradingStandardImpl.class);
        writerMap.put(SisImportWriter.class, SisImportImpl.class);
        writerMap.put(CommunicationChannelWriter.class, CommunicationChannelImpl.class);
        writerMap.put(FeatureFlagWriter.class, FeatureFlagImpl.class);
        writerMap.put(RubricWriter.class, RubricImpl.class);
    }
}
