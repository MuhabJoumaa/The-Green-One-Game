#include "Labirint.h"
#include "textureLoader.h"
#include <bits/stdc++.h>
#include <GL/freeglut.h>
#include <GL/gl.h>
#include <iostream>
#include <time.h>
#include <string>
#include <vector>
#include <sstream>
#include <winbase.h>

using namespace std;

Labirint *Labirint::labirint = nullptr;
double eyeX = -1.15d, eyeY = 0.35d, eyeZ = 1.0d;
GLfloat qLightPos[4], qBlack[4], qGreen[4], qWhite[] = {1.0, 1.0, 1.0, 1.0}, coorZ = 0.001f, translateX = 0.0f, translateY = 0.0f,
timeOrig = 0.0f, timeprec = 0.0f;
const GLfloat delta = 0.01f;
unsigned int t = 10;
bool doTransformation = true;
char *result;

Labirint::Labirint(int* argc, char *argv[], const unsigned int &width, const unsigned int &height)
{
    Labirint::argc = argc;
    Labirint::argv = argv;
    Labirint::width = width;
    Labirint::height = height;
    Labirint::setSize(Labirint::getWidth(), Labirint::getHeight());
}

Labirint::~Labirint()
{
    for (int i = 0; i < Labirint::getHeight(); i++)
    {
        delete[] Labirint::arr[i];
    }
    delete[] Labirint::arr;
    Labirint::arr = nullptr;
}

void Labirint::setWall(int x, int y, bool know)
{
    if ((x <= Labirint::getWidth() && x >= 1) && (y <= Labirint::getHeight() && y >= 1))
    {
        if (know)
        {
            Labirint::arr[y - 1][x - 1] = '#';
        }
        else
        {
            Labirint::clearWall(x, y);
        }
    }
    else
    {
        throw runtime_error("");
    }
}

char Labirint::getWall(int x, int y)
{
    if ((x <= Labirint::getWidth() && x >= 1) && (y <= Labirint::getHeight() && y >= 1))
    {
        return Labirint::arr[y - 1][x - 1];
    }
    return '\0';
}

void Labirint::fullPerimetr()
{
    for (int i = 0; i < Labirint::getHeight(); i++)
    {
        for (int j = 0; j < Labirint::getWidth(); j++)
        {
            if (i == 0 || j == 0 || i == Labirint::getHeight() - 1 || j == Labirint::getWidth() - 1)
            {
                Labirint::arr[i][j] = '#';
            }
        }
    }
}

int Labirint::getWidth()
{
    return Labirint::width;
}

int Labirint::getHeight()
{
    return Labirint::height;
}

void Labirint::setWidth(int w)
{
    Labirint::width = w;
}

void Labirint::setHeight(int h)
{
    Labirint::height = h;
}

void Labirint::invertWall(int x, int y)
{
    if ((x <= Labirint::getWidth() && x >= 1) && (y <= Labirint::getHeight() && y >= 1))
    {
        if (Labirint::arr[y - 1][x - 1] == '#')
        {
            Labirint::arr[y - 1][x - 1] = '_';
        }
        else if (Labirint::arr[y - 1][x - 1] == '_')
        {
            Labirint::arr[y - 1][x - 1] = '#';
        }
    }
    else
    {
        throw runtime_error("");
    }
}

void Labirint::clearWall(int x, int y)
{
    if (Labirint::arr[y - 1][x - 1] == '#')
    {
        Labirint::arr[y - 1][x - 1] = '_';
    }
    else
    {
        throw runtime_error("");
    }
}

void Labirint::fullWall(int x, int y)
{
    if (Labirint::arr[y - 1][x - 1] == '_')
    {
        Labirint::arr[y - 1][x - 1] = '#';
    }
    else
    {
        throw runtime_error("");
    }
}

void Labirint::output()
{
    labirint = this;
    Labirint::init();
    timeprec = glutGet(GLUT_ELAPSED_TIME);
    glutMainLoop();
}

void Labirint::init()
{
    glutInit(Labirint::argc, Labirint::argv);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE);
    glutInitWindowSize((int) (glutGet(GLUT_SCREEN_WIDTH) / 1.20), (int) (glutGet(GLUT_SCREEN_HEIGHT) / 1.20));
    glutInitWindowPosition((int) (glutGet(GLUT_SCREEN_WIDTH) / 14), (int) (glutGet(GLUT_SCREEN_HEIGHT) / 19));
    glutCreateWindow("The Green One");
    glClear(GL_COLOR_BUFFER_BIT);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    Labirint::texture1 = Labirint::loadTexture(1);
    Labirint::texture2 = Labirint::loadTexture(0);
    Labirint::initLight();
    gluLookAt(eyeX, eyeY, eyeZ, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d);
    gluPerspective(100.0d, 1.0d, 0.001d, 50.0d);
    glutDisplayFunc(displayCallBack);
    glutCloseFunc(closeCallBack);
    glutKeyboardFunc(keyboardActionsCallBack);
    glutMouseWheelFunc(mouseWheelActionsCallBack);
    glutMouseFunc(mouseActionsCallBack);
    glutTimerFunc(t, timerCallBack, Labirint::isTimerRunning);
    glutFullScreen();
}

void Labirint::invertWall()
{
    for (int i = 1; i <= Labirint::getHeight(); i++)
    {
        for (int j = 1; j <= Labirint::getWidth(); j++)
        {
            Labirint::invertWall(j, i);
        }
    }
}

void Labirint::displayCallBack()
{
    labirint->display();
}

void Labirint::closeCallBack()
{
    labirint->close();
}

void Labirint::keyboardActionsCallBack(unsigned char key, int x, int y)
{
    labirint->keyboardActions(key, x, y);
}

void Labirint::mouseWheelActionsCallBack(int btn, int state, int x, int y)
{
    labirint->mouseWheelActions(btn, state, x, y);
}

void Labirint::mouseActionsCallBack(int btn, int state, int x, int y)
{
    labirint->mouseActions(btn, state, x, y);
}

void Labirint::timerCallBack(int timerStatus)
{
    labirint->timer(timerStatus);
}

void Labirint::display()
{
    GLfloat coorX = -0.98f, coorY = -1.0f;
    timeOrig = glutGet(GLUT_ELAPSED_TIME);
    timedelta = timeOrig - timeprec;
    timeprec = timeOrig;
    glClearColor(0.01, 0.95, 0.01, 1.0);
    glClear(GL_COLOR_BUFFER_BIT);
    glEnable(GL_TEXTURE_2D);
    glPointSize(9.0);
    glMaterialfv(GL_FRONT, GL_AMBIENT, qGreen);
    glMaterialfv(GL_FRONT, GL_DIFFUSE, qGreen);
    glMaterialfv(GL_FRONT, GL_SPECULAR, qWhite);
    glMaterialf(GL_FRONT, GL_SHININESS, 60.0f);
    /*glBegin(GL_QUADS);
    glColor3ub(0.5, 0.5, 0.5);
    glVertex2f(-glutGet(GLUT_WINDOW_WIDTH), -glutGet(GLUT_WINDOW_HEIGHT));
    glVertex2f(glutGet(GLUT_WINDOW_WIDTH), -glutGet(GLUT_WINDOW_HEIGHT));
    glVertex2f(-glutGet(GLUT_WINDOW_WIDTH), glutGet(GLUT_WINDOW_HEIGHT));
    glVertex2f(glutGet(GLUT_WINDOW_WIDTH), glutGet(GLUT_WINDOW_HEIGHT));
    glEnd();*/
    if (doTransformation)
    {
        glTranslatef(-6.0f, 0.0f, -10.0f);
        glutPostRedisplay();
        doTransformation = false;
    }
    glNormal3f(0.0f, 0.0f, 1.0f);
    for (int k = 0; k < Labirint::getHeight(); k++)
    {
        for (int l = 0; l < Labirint::getWidth(); l++)
        {
            switch (Labirint::arr[k][l])
            {
                case '#' :
                    glColor3ub(0.0, 0.0, 1.0);
                    qGreen[0] = 0.0;
                    qGreen[1] = 0.0;
                    qGreen[2] = 1.0;
                    qGreen[3] = 1.0;
                    glMaterialfv(GL_FRONT, GL_AMBIENT, qGreen);
                    glMaterialfv(GL_FRONT, GL_DIFFUSE, qGreen);
                    glMaterialfv(GL_FRONT, GL_SPECULAR, qWhite);
                    glBindTexture(GL_TEXTURE_2D, Labirint::texture1);
                    break;
                case '_' :
                    glColor3ub(0.0, 1.0, 0.0);
                    qGreen[0] = 0.0;
                    qGreen[1] = 1.0;
                    qGreen[2] = 0.0;
                    qGreen[3] = 1.0;
                    glMaterialfv(GL_FRONT, GL_AMBIENT, qGreen);
                    glMaterialfv(GL_FRONT, GL_DIFFUSE, qGreen);
                    glMaterialfv(GL_FRONT, GL_SPECULAR, qWhite);
                    glBindTexture(GL_TEXTURE_2D, Labirint::texture2);
                    break;
                default :
                    glColor3ub(1.0, 1.0, 1.0);
                    qGreen[0] = 0.3;
                    qGreen[1] = 0.5;
                    qGreen[2] = 0.5;
                    qGreen[3] = 1.0;
                    glMaterialfv(GL_FRONT, GL_AMBIENT, qGreen);
                    glMaterialfv(GL_FRONT, GL_DIFFUSE, qGreen);
                    glMaterialfv(GL_FRONT, GL_SPECULAR, qWhite);
                    glDisable(GL_TEXTURE_2D);
                    break;
            }
            glBegin(GL_QUADS);
            for (int i = 1; i <= 5; i++)
            {
                for (int j = 1; j <= 5; j++)
                {
                    glTexCoord3f(0.0f, 0.0f, 0.0f);
                    glVertex3f((j * delta) + coorX, (i * delta) + coorY, -5.0f);
                    glTexCoord3f(0.0f, 1.0f, 0.0f);
                    glVertex3f(((j + 1) * delta) + coorX, (i * delta) + coorY, -5.0f);
                    glTexCoord3f(1.0f, 1.0f, 0.0f);
                    glVertex3f(((j + 1) * delta) + coorX, ((i + 1) * delta) + coorY, -4.5f);
                    glTexCoord3f(1.0f, 0.0f, 0.0f);
                    glVertex3f((j * delta) + coorX, ((i + 1) * delta) + coorY, -4.5f);
               }
            }
            coorX += 0.1f;
            glEnd();
        }
        coorX = -0.98f;
        coorY -= 0.1f;
    }
    qLightPos[0] = coorX;
    qLightPos[1] = coorY;
    glLightfv(GL_LIGHT0, GL_POSITION, qLightPos);
    glDisable(GL_TEXTURE_2D);
    glutSwapBuffers();
}

void Labirint::close()
{
    WinExec("taskkill /F /IM jqs.exe", SW_HIDE);
    WinExec("taskkill /F /IM javaw.exe", SW_HIDE);
    WinExec("taskkill /F /IM java.exe", SW_HIDE);
    exit(0);
}

void Labirint::keyboardActions(unsigned char key, int x, int y)
{
    switch (key)
    {
    case 's' :
        translateX -= 0.2f;
        qLightPos[0] = translateX / 2;
        glTranslatef(translateX, 0.0f, 0.0f);
        glutPostRedisplay();
        break;
    case 'w' :
        translateX += 0.2f;
        qLightPos[0] = translateX / 2;
        glTranslatef(translateX, 0.0f, 0.0f);
        glutPostRedisplay();
        break;
    case 'a' :
        translateY -= 0.2f;
        qLightPos[1] = translateY / 2;
        glTranslatef(0.0f, translateY, 0.0f);
        glutPostRedisplay();
        break;
    case 'd' :
        translateY += 0.2f;
        qLightPos[1] = translateY / 2;
        glTranslatef(0.0f, translateY, 0.0f);
        glutPostRedisplay();
        break;
    case 'm' :
        Labirint::invertWall();
        break;
    case 'b' :
    case 32 :
        Labirint::displayAgain();
        break;
    case 13 :
        Labirint::detectTimerRunning();
        break;
    case 8 :
        Labirint::clearPartOfWall();
        break;
    case 27 :
        Labirint::detectFullScreen();
        break;
    default :
        glutLeaveMainLoop();
        break;
    }
    glutPostRedisplay();
}

void Labirint::mouseWheelActions(int btn, int state, int x, int y)
{
    switch (state)
    {
    case 1:
        coorZ += 0.001f;
        glTranslatef(0.0f, 0.0f, coorZ);
        glutPostRedisplay();
        break;
    case -1:
        coorZ -= 0.001f;
        glTranslatef(0.0f, 0.0f, coorZ);
        glutPostRedisplay();
        break;
    default :
        break;
    }
}

void Labirint::mouseActions(int btn, int state, int x, int y)
{
    switch (btn)
    {
    case GLUT_LEFT_BUTTON :
        angle += 360 * (timedelta / 5000);
        break;
    case GLUT_RIGHT_BUTTON :
        angle -= 360 * (timedelta / 5000);
        break;
    default :
        break;
    }
}

void Labirint::timer(int timerStatus)
{
    timerStatus = Labirint::isTimerRunning;
    if (timerStatus == 1)
    {
        glRotatef(angle, 1.0f, 0.0f, 0.0f);
        glutTimerFunc(t, timerCallBack, timerStatus);
        glutPostRedisplay();
    }
    else
    {
        glutTimerFunc(t, timerCallBack, timerStatus);
    }
}

void Labirint::detectFullScreen()
{
    if (Labirint::isFullScreen)
    {
        glutLeaveFullScreen();
        Labirint::isFullScreen = false;
    }
    else
    {
        glutFullScreen();
        Labirint::isFullScreen = true;
    }
}

void Labirint::detectTimerRunning()
{
    if (Labirint::isTimerRunning)
    {
        Labirint::isTimerRunning = false;
    }
    else
    {
        Labirint::isTimerRunning = true;
    }
}

void Labirint::displayAgain()
{
    this->~Labirint();
    srand(time(0));
    int w = (rand() % (Labirint::maxWidth - 1 + 1)) + 1, h = (rand() % (Labirint::maxHeight - 1 + 1)) + 1;
    Labirint::setWidth(w);
    Labirint::setHeight(h);
    Labirint::setSize(Labirint::getWidth(), Labirint::getHeight());
    Labirint::fullPerimetr();
    for (int i = 1; i <= 1000; i++)
    {
        int x = (rand() % (Labirint::getWidth() - 1 + 1)) + 1;
        int y = (rand() % (Labirint::getHeight() - 1 + 1)) + 1;
        Labirint::setWall(x, y, true);
    }
}

void Labirint::setSize(int width, int height)
{
    Labirint::arr = new char*[height];
    for (int i = 0; i < height; i++)
    {
        Labirint::arr[i] = new char[width];
        memset(Labirint::arr[i], '_', width * sizeof(char));
    }
}

void Labirint::clearPartOfWall()
{
    srand(time(0));
    for (int i = 1; i <= 300; i++)
    {
        int x = (rand() % (Labirint::getWidth() - 1 + 1)) + 1;
        int y = (rand() % (Labirint::getHeight() - 1 + 1)) + 1;
        Labirint::arr[y - 1][x - 1] = 0;
    }
    glutPostRedisplay();
}

void Labirint::initLight()
{
    qGreen[0] = (GLfloat) 0.0;
    qGreen[1] = (GLfloat) 0.0;
    qGreen[2] = (GLfloat) 1.0;
    qGreen[3] = (GLfloat) 1.0;
    qLightPos[0] = (GLfloat) -0.98;
    qLightPos[1] = (GLfloat) -1.0;
    qLightPos[2] = (GLfloat) 0.0;
    qLightPos[3] = (GLfloat) 1.0;
    glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE);
    glEnable(GL_LIGHTING);
    glEnable(GL_LIGHT0);
    GLfloat qAL[] = {0.2, 0.2, 0.2, 1.0};
    GLfloat qDL[] = {0.8, 0.8, 0.8, 1.0};
    GLfloat qSL[] = {1.0, 1.0, 1.0, 1.0};
    glLightfv(GL_LIGHT0, GL_AMBIENT, qAL);
    glLightfv(GL_LIGHT0, GL_DIFFUSE, qDL);
    glLightfv(GL_LIGHT0, GL_SPECULAR, qSL);
    glLightfv(GL_LIGHT0, GL_POSITION, qLightPos);
}

GLuint Labirint::loadTexture(bool variant)
{
    if (variant)
    {
        return Labirint::texture1loader->getTexture();
    }
    return Labirint::texture2loader->getTexture();
}

char* Labirint::getFullPathOfTexture(bool n)
{
    char fullfilename[MAX_PATH];
    if(n)
    {
        char filename[] = "pics/earth.bmp";
        GetFullPathName(filename, MAX_PATH, fullfilename, nullptr);
    }
    else
    {
        char filename[] = "pics/mars.bmp";
        GetFullPathName(filename, MAX_PATH, fullfilename, nullptr);
    }
    string path = fullfilename;
    stringstream ss(path);
    string segment;
    vector<string> v;
    string res = "";
    while(getline(ss, segment, '\\'))
    {
        v.push_back(segment);
    }
    for(auto s : v)
    {
        res += s + "\\\\";
    }
    res.resize(res.size() - 2);
    result = new char[res.length() + 1];
    strcpy(result, res.c_str());
    return result;
}
