package ci.poc;

import com.cg.helix.util.Objects;

/**
 * Date of creation: 29.09.2016
 * <p>
 * Copyright \(c\) CompuGROUP Software GmbH,
 * This software is the confidential and proprietary information of
 * CompuGROUP Software GmbH. You shall not disclose such confidential
 * information and shall use it only in accordance with the terms of
 * the license agreement you entered into with CompuGROUP Software GmbH.
 */
public class G3MicroServiceImpl implements G3MicroService {
    public int getCount() {
        // returns 300
        return 300;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }
}
