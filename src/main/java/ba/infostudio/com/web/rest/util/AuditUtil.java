package ba.infostudio.com.web.rest.util;


import ba.infostudio.com.domain.Action;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;

public final class AuditUtil {
    private AuditUtil() {

    }

    public static AuditApplicationEvent createAuditEvent(String principal, String type, String entityName, String entityId, Action action ){
        switch (action) {
            case POST:
                return new AuditApplicationEvent(
                        principal,
                        type,
                        "message=" +  HeaderUtil.createEntityCreationAlert(entityName, entityId)
                );
            case PUT:
                return new AuditApplicationEvent(
                        principal,
                        type,
                        "message=" +  HeaderUtil.createEntityUpdateAlert(entityName, entityId)
                );
            case DELETE:
                return new AuditApplicationEvent(
                        principal,
                        type,
                        "message=" +  HeaderUtil.createEntityDeletionAlert(entityName, entityId)
                );

            case ARCHIVE:
                return new AuditApplicationEvent(
                    principal,
                    type,
                    "message=" +  HeaderUtil.createEntityDeletionAlert(entityName, entityId)
                );
        }
        return null;
    }
}
