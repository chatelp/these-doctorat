% Exemple de classe de DEA construite au-dessus de thloria.
%
\NeedsTeXFormat{LaTeX2e}[1997/12/01]
\ProvidesClass{dealoria}[2001/08/01 (D. Roegel, LORIA))]
\DeclareOption*{\PassOptionsToClass{\CurrentOption}{thloria}}
\ProcessOptions
\LoadClass[openany]{thloria}
\renewcommand{\@NancyIhe@d}{{\UseEntryFont{ThesisFirstPageHead}\noindent
    \centerline{Universit\'e Henri Poincar\'e -- Nancy I}%
    \par\nointerlineskip
    \vskip1mm
    \noindent\hrulefill%\\
    %D\'epartement de Formation Doctorale en Informatique
    %                             \hfill\'Ecole Doctorale IAE $+$ M\par
    }}
\newcommand\DeaNancyI{\renewcommand{\@ThesisFirstPageHead}{\@NancyIhe@d}%
                         \ThesisDiploma{{\UseEntryFont{ThesisDiploma}%
             DEA de l'Universit\'e Henri Poincar\'e -- Nancy I\\[3mm]
            {\UseEntryFont{ThesisSpecialty}(Sp\'ecialit\'e Informatique)}}}}
\newcommand{\@Metzhe@d}{{\UseEntryFont{ThesisFirstPageHead}\noindent
    \centerline{Universit\'e de Metz}%
    \par\nointerlineskip
    \vskip1mm
    \noindent\hrulefill%\\
    %D\'epartement de Formation Doctorale en Informatique
    %                             \hfill\'Ecole Doctorale IAE $+$ M\par
    }}
\newcommand\DeaMetz{\renewcommand{\@ThesisFirstPageHead}{\@Metzhe@d}%
                         \ThesisDiploma{{\UseEntryFont{ThesisDiploma}%
             DEA de l'Universit\'e de Metz\\[3mm]
            {\UseEntryFont{ThesisSpecialty}(Sp\'ecialit\'e Informatique)}}}%
            \ThesisFirstPageFoot{\@ThesisFirstPageFoot@LITAMetz}%
                   }

\newcommand{\@ThesisFirstPageFoot@LITAMetz}%
    {\hrule
     \vskip2mm
     \centerline{\UseEntryFont{ThesisFirstPageFoot}%
                 LITA Metz}
     \centerline{\if@logo@loria@
                   % $\raise\baselineskip\hbox{\LoriaLogo}$\hskip2.5cm%
                 \fi}
     \vskip2mm
    }
\ClearJury
\NewJuryCategory{Membre}{}{}
\NewJuryCategory{Encadrant}{\textit{Encadrant :}}{\textit{Encadrants :}}

\let\DeaKind\ThesisKind
\let\DeaInOrderToGet\ThesisInOrderToGet
\let\DeaPresentedThe\ThesisPresentedThe
\let\DeaTitle\ThesisTitle
\let\DeaAuthor\ThesisAuthor
\let\MakeDeaTitlePage\MakeThesisTitlePage
\DeaKind{M\'EMOIRE}
\DeaInOrderToGet{pour l'obtention du}
\DeaPresentedThe{soutenu le ...}
\DeaTitle{Le titre}
\DeaAuthor{Moi}
\endinput