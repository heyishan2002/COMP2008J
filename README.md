# Comp2008J-20



## Getting started

Tianjun_Zang 21207446
Yishan_He 21207335
Ziyi_Rong 21207362
Bingyan_Zhu 21207356
Github:
https://github.com/heyishan2002/COMP2008J.git

git@github.com:heyishan2002/COMP2008J.git

Gitlab: 
https://gitlab.com/yishan.he/comp2008j-20.git

git@gitlab.com:yishan.he/comp2008j-20.git

 (If github have some problems, you can see our project from gitlab)

Contribution:
Since it was our first time to use git for team cooperation, our team was not proficient in many areas. We carried out many project rollback and architecture adjustment, which involved a lot of replication operations. Therefore, the contribution of some team members was unusually high, but in fact, it was not that high, and a large part of it was repeated operations. Therefore, the contribution on GitHub is not the true contribution of our team, and the detailed division of labor of our team is in this pdf.
ZangTianjun: I was responsible for most of the back-end work and built the back-end framework. Some back-end methods are written. Fix back-end bugs.
HeYishan: I took charge of the front-end work and built the front-end framework. Write the front-end method. Write the back-end part of the card class.
ZhuBingyan: Responsible for writing front-end methods. Fixed front-end bugs. Write the back-end part of the card class.
RongZiyi: Responsible for writing back-end methods. Also participate in the front-end methods. Write the back-end part of the card class.

Description of our project:
View

The interface of the program is designed based on java swing. In the custom GraphicInterface class as a graphical display interface class, which contains the mainFrame as the program frame, gPanel as the program graphical interface display panel. The program defines its own panel implementation with GamePanel, derived from JPanel, which contains four playerWindow objects that are used to display each of the four players' respective areas in gPanel. The PlayerWindow class defines what to display within each player's area, including the drawBackground that displays the player's area, the drawCards that display the player's cards, and so on.
The program defines the MInterface class used to interface with the game logic part of the program. The game logic calls the gameInterface method of the MInterface every time it needs to render a card and ask the player to select it. Before calling this method, the game logic needs to set the cards or areas that might be selected as selectable and tell the MInterface which buttons to use to confirm the action. The MInterface uses the custom MButton to implement the button function. When the button is pressed, the gameInterface returns the pressed button as a return value to the caller. After the game logic calls gameInterface, when the player clicks on the game interface, the PlayerWindow object will detect the click location. If the click is in the range of the selectable object, the selected attribute of the object will be set to true. The caller determines which objects are selected by looking at the Selected property of all pending objects.

Control：


In the control part, gmain is used as the entrance of the whole program, the Game class of the logical part is called, the logical part of the game is initialized, and the game is run. Control the run and stop of the game in the gameStart method. The front end is called when the game stops, showing the winner in the Player. At the same time, the front-end is invoked to implement interface initialization

Model：

In this part, the logical part of the game is realized. The player class encapsulates some methods that the player needs to operate, as well as the methods of the player's property, bank, hand cards and other materials and their corresponding needs. The player's round method defines the operations that the player can carry out in his round. In addition, the definitions of the various cards are also defined here. The Selectable class is used as the parent class for all cards and the player area to mark whether the area is selected. All cards belong to the Card class and there are two kinds of cards: Bankable and Property, property cards represent property of various colors, Bankable is divided into, Money, ActionCard, action card inherits ActionCard class, encapsulates the use effect of each action card (some of the effects of hand cards are defined in the Player class). By calling the use method inside the realization of the player using the action card of various operations.


Framework implementation:
Mvc: The overall project results are assigned according to contraol,model, and view
Singleton pattern: The gmain method implements the singleton pattern
Facade pattern: There are many implementations of this pattern in game, player, etc
Observer mode: When we implement the property in the player class, we complete the implementation of the observer mode. When the property state changes, the player's corresponding industry state card state will also change



## Add your files

- [ ] [Create](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#create-a-file) or [upload](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#upload-a-file) files
- [ ] [Add files using the command line](https://docs.gitlab.com/ee/gitlab-basics/add-file.html#add-a-file-using-the-command-line) or push an existing Git repository with the following command:

```
cd existing_repo
git remote add origin https://gitlab.com/yishan.he/comp2008j-20.git
git branch -M main
git push -uf origin main
```

## Integrate with your tools

- [ ] [Set up project integrations](https://gitlab.com/yishan.he/comp2008j-20/-/settings/integrations)

## Collaborate with your team

- [ ] [Invite team members and collaborators](https://docs.gitlab.com/ee/user/project/members/)
- [ ] [Create a new merge request](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html)
- [ ] [Automatically close issues from merge requests](https://docs.gitlab.com/ee/user/project/issues/managing_issues.html#closing-issues-automatically)
- [ ] [Enable merge request approvals](https://docs.gitlab.com/ee/user/project/merge_requests/approvals/)
- [ ] [Automatically merge when pipeline succeeds](https://docs.gitlab.com/ee/user/project/merge_requests/merge_when_pipeline_succeeds.html)

## Test and Deploy

Use the built-in continuous integration in GitLab.

- [ ] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/index.html)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing(SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)
- [ ] [Deploy to Kubernetes, Amazon EC2, or Amazon ECS using Auto Deploy](https://docs.gitlab.com/ee/topics/autodevops/requirements.html)
- [ ] [Use pull-based deployments for improved Kubernetes management](https://docs.gitlab.com/ee/user/clusters/agent/)
- [ ] [Set up protected environments](https://docs.gitlab.com/ee/ci/environments/protected_environments.html)

***

# Editing this README

When you're ready to make this README your own, just edit this file and use the handy template below (or feel free to structure it however you want - this is just a starting point!). Thank you to [makeareadme.com](https://www.makeareadme.com/) for this template.

## Suggestions for a good README
Every project is different, so consider which of these sections apply to yours. The sections used in the template are suggestions for most open source projects. Also keep in mind that while a README can be too long and detailed, too long is better than too short. If you think your README is too long, consider utilizing another form of documentation rather than cutting out information.

## Name
Choose a self-explaining name for your project.

## Description
Let people know what your project can do specifically. Provide context and add a link to any reference visitors might be unfamiliar with. A list of Features or a Background subsection can also be added here. If there are alternatives to your project, this is a good place to list differentiating factors.

## Badges
On some READMEs, you may see small images that convey metadata, such as whether or not all the tests are passing for the project. You can use Shields to add some to your README. Many services also have instructions for adding a badge.

## Visuals
Depending on what you are making, it can be a good idea to include screenshots or even a video (you'll frequently see GIFs rather than actual videos). Tools like ttygif can help, but check out Asciinema for a more sophisticated method.

## Installation
Within a particular ecosystem, there may be a common way of installing things, such as using Yarn, NuGet, or Homebrew. However, consider the possibility that whoever is reading your README is a novice and would like more guidance. Listing specific steps helps remove ambiguity and gets people to using your project as quickly as possible. If it only runs in a specific context like a particular programming language version or operating system or has dependencies that have to be installed manually, also add a Requirements subsection.

## Usage
Use examples liberally, and show the expected output if you can. It's helpful to have inline the smallest example of usage that you can demonstrate, while providing links to more sophisticated examples if they are too long to reasonably include in the README.

## Support
Tell people where they can go to for help. It can be any combination of an issue tracker, a chat room, an email address, etc.

## Roadmap
If you have ideas for releases in the future, it is a good idea to list them in the README.

## Contributing
State if you are open to contributions and what your requirements are for accepting them.

For people who want to make changes to your project, it's helpful to have some documentation on how to get started. Perhaps there is a script that they should run or some environment variables that they need to set. Make these steps explicit. These instructions could also be useful to your future self.

You can also document commands to lint the code or run tests. These steps help to ensure high code quality and reduce the likelihood that the changes inadvertently break something. Having instructions for running tests is especially helpful if it requires external setup, such as starting a Selenium server for testing in a browser.

## Authors and acknowledgment
Show your appreciation to those who have contributed to the project.

## License
For open source projects, say how it is licensed.

## Project status
If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.
