/*
 * The MIT License
 *
 * Copyright (c) 2010 - 2011, Tomasz Blaszczynski, Emanuele Zattin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.jenkinsci.plugins.periodicbackup;

import hudson.Extension;
import hudson.model.RestartListener;

import java.io.IOException;

/**
 *
 * This RestartListener will veto the restart of Hudson (via safeRestart) while a restore is ongoing.
 */
@Extension
public class PeriodicBackupRestartListener extends RestartListener {

    private boolean periodicBackupReady = true;

    public void ready() {
        periodicBackupReady = true;
    }

    public void notReady() {
        periodicBackupReady = false;
    }

    @Override
    public boolean isReadyToRestart() throws IOException, InterruptedException {
        return periodicBackupReady;
    }

    public static PeriodicBackupRestartListener get() {
        return RestartListener.all().get(PeriodicBackupRestartListener.class);
    }
}
