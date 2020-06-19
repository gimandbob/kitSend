package covidtest;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Send_table")
public class Send {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long inspectionId;
    private Long kitId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        Sent sent = new Sent();
        BeanUtils.copyProperties(this, sent);
        sent.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){
        SendCancelled sendCancelled = new SendCancelled();
        BeanUtils.copyProperties(this, sendCancelled);
        sendCancelled.publishAfterCommit();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(Long inspectionId) {
        this.inspectionId = inspectionId;
    }
    public Long getKitId() {
        return kitId;
    }

    public void setKitId(Long kitId) {
        this.kitId = kitId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
