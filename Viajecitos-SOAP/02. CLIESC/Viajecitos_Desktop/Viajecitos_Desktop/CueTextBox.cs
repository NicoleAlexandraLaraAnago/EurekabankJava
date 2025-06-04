using System;
using System.Runtime.InteropServices;
using System.Windows.Forms;

namespace Viajecitos_Desktop
{
    public class CueTextBox : TextBox
    {
        [DllImport("user32.dll", CharSet = CharSet.Unicode)]
        private static extern int SendMessage(IntPtr hWnd, int msg, int wParam, string lParam);

        private const int EM_SETCUEBANNER = 0x1501;
        private string _cue = string.Empty;

        public string Cue
        {
            get { return _cue; }
            set { _cue = value; if (IsHandleCreated) SetCue(); }
        }

        protected override void OnHandleCreated(EventArgs e)
        {
            base.OnHandleCreated(e);
            SetCue();
        }
        private void SetCue() => SendMessage(Handle, EM_SETCUEBANNER, 0, _cue);
    }
}
