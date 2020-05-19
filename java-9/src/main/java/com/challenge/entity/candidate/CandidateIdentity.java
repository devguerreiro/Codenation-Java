package com.challenge.entity.candidate;

import com.challenge.entity.acceleration.Acceleration;
import com.challenge.entity.company.Company;
import com.challenge.entity.user.User;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CandidateIdentity implements Serializable {

    @ManyToOne
    @NotBlank
    private User userId;

    @ManyToOne
    @NotNull
    private Acceleration accelerationId;

    @ManyToOne
    @NotNull
    private Company companyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateIdentity that = (CandidateIdentity) o;
        return userId.equals(that.userId) &&
                accelerationId.equals(that.accelerationId) &&
                companyId.equals(that.companyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, accelerationId, companyId);
    }
}
