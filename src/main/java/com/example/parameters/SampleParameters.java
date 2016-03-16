package com.example.parameters;

/**
 * Created by IntelliJ IDEA.
 * <p/>
 * Created :  15/03/16 7:20 PM MST
 * <p/>
 * Modified : $Date$ UTC
 * <p/>
 * Revision : $Revision$
 *
 * @author Trenton D. Adams
 */
public enum SampleParameters implements ParameterEnum
{
    parameter1(10,"Parameter 1"),
    parameter2(20,"Parameter 2"),
    parameter3(30,"Parameter 3"),
    ;

    private final int maxLength;
    private final String displayName;

    SampleParameters(final int maxLength, final String displayName)
    {
        this.maxLength = maxLength;
        this.displayName = displayName;
    }

    @Override
    public int getMaxLength()
    {
        return maxLength;
    }

    @Override
    public String getDisplayName()
    {
        return displayName;
    }
}
