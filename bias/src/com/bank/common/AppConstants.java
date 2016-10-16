package com.bank.common;



public class AppConstants {

    public enum HttpMethod {
        GET,POST,PUT,DELETE
    }

    public static final String HTTP_PROTOCOL = "http";
    public static final String PATH_VAR_REGEX = "/\\$([^\\s|/]+)";
    public static final String HTTPS_PROTOCOL = "https";

    public static final String BEAN_NAME_ACL_SERVICE = "ACLService";

    // Encoding
    public static final String UTF_ENCODE = "UTF-8";

    // symbol part
    public static final String STRIKE = "-";
    public static final String COMMA = ",";
    public static final String EMPTY = "";
    public static final String DOT = ".";
    public static final String NEWLINE = "\n";
    public static final String CHINESE_SYMBOL = "[\u4e00-\u9fa5]";
    public static final String SYSTEM_ENCODING = "file.encoding";

    // error code
    public static final Integer ERROR_CODE_NOT_LOGIN = 1;
    // error message "NotLogin"
    public static final String ERROR_MSG_NOT_LOGIN = "NotLogin";
    public static final String PAGINATION_DTO = "paginationDTO";

    //import excel
    public static final String IMPORT_EXCEL_DATA = "importExcelData";
    public static final String KEY_ERROR_IMPORT_EXCEL = "importExcelError";
    public static final String PARSE_SHEET_ERROR = "parseSheetError";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String KEY_ERROR_MESSAGE_NO_FILE = "The file does not exist";
    public static final String IMPORT_EXCEL_PARAM = "param";

    public static final String ORGANIZATION_ID = "organizationId";
    public static final String IN_ORG_ID = "inOrgId";
    public static final String USER_ID = "userId";
    public static final String ROLE_ID = "roleId";
    public static final String PREVIOUS_ROLE_ID = "previousRoleId";
    public static final String CURRENT_ROLE_ID = "currentRoleId";
    public static final String USER = "user";
    public static final String ROLES = "roles";
    public static final String CURRENT_ROLE_CODE = "currentRoleCode";
    public static final String USER_NAME = "userName";
    public static final String ORG_ID = "orgId";
    public static final String DISPLAY_INACTIVE = "displayInactive";
    public static final String ID = "id";
    public static final String DELETED = "deleted";
    public static final String KEY_WORDS = "keyWords";
    public static final String KEYWORDS = "keywords";
    public static final String GROUP_ID = "groupId";
    public static final String GROUP_ADMIN = "groupAdmin";
    public static final String SORT_SIGN = "sortSign";
    public static final String SORT_NAME = "sortName";
    public static final String DESC_ORDER = "desc";
    public static final String ASC_ORDER = "asc";
    public static final String ORIGINAL_ICON = "originalIcon";
    public static final String ICON = "icon";
    public static final String SMALL_ICON = "smallIcon";
    //message
    public static final String MESSAGE = "message";
    public static final String VISIBILITY = "visibility";
    public static final String MESSAGE_BODY = "body";
    public static final String MESSAGE_TITLE = "title";
    public static final String MESSAGE_FROM_USER_ID = "fromUserId";
    public static final String MESSAGE_ENTITY_ID = "entityId";
    public static final String MESSAGE_SENT_ID = "sentId";
    public static final String MESSAGE_SENT_TYPE = "sentType";
    public static final Integer MESSAGE_PAGE_SIZE = 8;
    public static final String MESSAGE_NEW_MESSAGE_COUNT = "newMessageCount";
    public static final String MESSAGE_TYPE = "messageType";
    public static final String MESSAGE_TYPE_ALL = "allMessage";

    //sent message
    public static final String SENT_MESSAGE_GROUP_TYPE = "groupType";

    // I18N key
    public static final String LOCALE = "locale";
    public static final String LOCALE_ZH_CN = "zh_CN";
    public static final String LOCALE_EN_US = "en_US";

    // URL
    public static final String REQUESTED_URI = "requestedURI";
    public static final String STATIC_URL = "staticURL";
    public static final String SH_DFS_URL = "dfsURL";

    public static final String FORMMAT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String TIMEZONE_AISA_SHANGHAI = "Asia/Shanghai";

    public static final String PERCENT_SIGH = "%";
    public static final String ZERO_STRING = "0";

    public static final String EVENT_DELETE_USER = "deleteUser";
    public static final String EVENT_UPDATE_USER = "updateUser";
    public static final String EVENT_ADD_USER = "addUser";
    public static final String EVENT_INIT_PROPERTY = "initProperty";
    public static final String EVENT_DELETE_ORG = "deleteOrganization";
    public static final String EVENT_DELETE_GROUP = "deleteGroup";
    public static final String EVENT_ADD_GROUP = "addGroup";
    public static final String EVENT_UPDATE_GROUP_TOTAL_ADMINS = "updateGroupTotalAdmins";
    public static final String EVENT_UPDATE_GROUP_TOTAL_MEMBERS = "updateGroupTotalMembers";
    public static final String EVENT_BEFORE_LOGIN = "beforeLogin";
    public static final String EVENT_AFTER_LOGOUT = "afterLogout";
    public static final String EVENT_RETURN_MSG_KEY = "returnKey";

    public static final String KEY_ERROR_MSG_DUPLICATE_USER_NAME_ERROR = "duplicateUserNameError";
    public static final String KEY_ERROR_USER_NAME_EXIST = "userNameExist";
    public static final String KEY_ERROR_FILE_NOT_FOUND = "uploadFileNotFound";
    public static final String KEY_ERROR_UNKNOWN = "systemError";
    public static final String KEY_ERROR_MSG_USER_NAME_ERROR = "userNameError";
    public static final String KEY_ERROR_MSG_PASSWORD_ERROR = "passwordError";
    public static final String KEY_ERROR_MSG_NOT_ADMIN = "notAdmin";
    public static final String KEY_ERROR_MSG_NOT_ACCESS = "NoAccess";

    public static final String DEFAULT_USER_ICON = "defaultUserIconPath";
    public static final String DEFAULT_USER_SAMLL_ICON = "defaultUserSamllIconPath";

    public static final String PROPERTY = "property";
    public static final String EXPORT_TEMP_DATA_PATH = "templates/temp_data/";
    public static final String EXPORT_EXCEL_SUFFIX = ".xls";

    public static final String GROUP_TOTAL_ADMINS = "totalAdmins";
    public static final String GROUP_TOTAL_MEMBERS = "totalMembers";
    public static final String ORG_TOTAL_GROUPS = "totalGroups";
    public static final String ROLE_CODE_ORGANIZATION_ADMIN = "organizationAdmin";
    public static final String ROLE_CODE_GROUP_ADMIN = "groupAdmin";
    public static final String GROUP_ADMINS = "groupAdmins";

    public static final Integer DEFAULT_ZERO = 0;
    public static final Integer DEFAULT_ORDER_ONE = 1;
    public static final Float DEFAULT_FLOAT_ZERO = 0f;
    public static final Integer IS_GROUP_ADMIN = 1;
    public static final Integer IS_GROUP_MEMBER = 0;

    public static final String ORGANIZATION_STATUS_APPROVED = "approved";
    public static final String FAILURE_LIST = "failureList";
    public static final String SUCCESS_LIST = "successList";

    public static final String CURRENT_USER = "currentUser";
    public static final Integer DEFAULT_3_LINE_PAGE_SIZE = 8;
    public static final String CURRENT_ROLE = "currentRole";
    public static final String PRIVILEGES = "privileges";
    public static final String PRIVILEGE_TYPE_MENU = "menu";

    public static final String USER_FIRST_LOGIN = "login";
    public static final String REQUEST = "request";

    // cms page
    public static final String CMS_PAGE_URL_EXISTING = "CMSPageUrlExisting";
    public static final String CMS_BLOCK_EXISTING = "CMSBlockExisting";
    public static final String CMS_BLOCK_NOT_EXIST = "CMSBlockNotExist";
    public static final String CMS_BLOCK_REGEX = "(<aug:(\\w*).*?/>)|(<aug:(\\w*).*?></aug:(\\w*).*?>)";
    public static final String CMS_BLOCK_TYPE = "cmsBlock";
    public static final String CMS_BLOCK_ATTRIBUTE_NAME = "name=\"(.*?)\"";
    public static final String CMS_BLOCK_NAME = "blockName";
    public static final String DOLLAR_SYMBOL = "$";
    public static final String DOLLAR_REPLACED_SYMBOL = "\\$";

    // storage file type
    public static final String VIDEO = "video";
    public static final String COMPRESSED_PACKAGE = "compressedPackage";
    public static final String PICTURE = "picture";
    public static final String DOCUMENT = "document";

    // file suffix name
    // video suffix
    public static final String FILE_SUFFIX_MP4 = ".mp4";
    // compressed package suffix
    public static final String FILE_SUFFIX_RAR = ".rar";
    public static final String FILE_SUFFIX_ZIP = ".zip";
    public static final String FILE_SUFFIX_GZ = ".gz";
    public static final String FILE_SUFFIX_7Z = ".7z";
    // picture suffix
    public static final String FILE_SUFFIX_JPG = ".jpg";
    public static final String FILE_SUFFIX_CAPITAL_JPG = ".JPG";
    public static final String FILE_SUFFIX_JPEG = ".jpeg";
    public static final String FILE_SUFFIX_CAPITAL_JPEG = ".JPEG";
    public static final String FILE_SUFFIX_PNG = ".png";
    public static final String FILE_SUFFIX_CAPITAL_PNG = ".PNG";
    public static final String FILE_SUFFIX_GIF = ".gif";
    public static final String FILE_SUFFIX_BMP = ".bmp";
    public static final String FILE_SUFFIX_PIC = ".pic";

    public static final String APP_CONTEXT_SESSION = "APP_CONTEXT_SESSION";
    public static final String ERROR_FILDS = "ERROR_FILDS";

    public static final String APP_CONTEXT_REQUEST = "APP_CONTEXT_REQUEST";
    public static final String APP_CONTEXT_RESPONSE = "APP_CONTEXT_RESPONSE";

    public static final String APP_URL_PREFIX = "page";
    public static final String CURRENT_DATE = "date";
    public static final String CURRENT_WEEK = "week";
}
