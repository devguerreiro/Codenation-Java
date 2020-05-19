package com.challenge.entity.submission;

import com.challenge.entity.user.User;
import com.challenge.entity.challenge.Challenge;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubmissionIdentity implements Serializable {

    @ManyToOne
    @NotNull
    private User userId;

    @ManyToOne
    @NotNull
    private Challenge challengeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmissionIdentity that = (SubmissionIdentity) o;
        return userId.equals(that.userId) &&
                challengeId.equals(that.challengeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, challengeId);
    }
}
