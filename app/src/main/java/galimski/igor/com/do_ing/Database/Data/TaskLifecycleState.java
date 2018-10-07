package galimski.igor.com.do_ing.Database.Data;

import galimski.igor.com.do_ing.R;

public enum TaskLifecycleState
{
    CREATED,
    UPDATED,
    COMPLETED,
    DELETED;

    public int GetLifecycleString(TaskLifecycleState taskLifecycle)
    {
        switch (taskLifecycle)
        {
            case CREATED:
            {
                return R.string.create_task_lifecycle;
            }

            case UPDATED:
            {
                return R.string.update_task_lifecycle;
            }

            case COMPLETED:
            {
                return R.string.complete_task_lifecycle;
            }

            case DELETED:
            {
                return R.string.delete_task_lifecycle;
            }
        }

        return 0;
    }
}
